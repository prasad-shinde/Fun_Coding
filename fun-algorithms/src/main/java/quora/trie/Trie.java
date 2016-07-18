package quora.trie;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import quora.heap.Heap;
import quora.util.QuoraUtil;

/**Trie class : The data strings are stored in the trie for efficient retrieval of search strings. The ids
 * are stored in the nodes as a max heap based on the score.
 * @author Prasad
 *
 */
public class Trie {
	private TrieNode root;
	
	public Trie() {
		root = new TrieNode();
	}
	
	/** adds the data string to the trie. for every node in the trie we maintain a max heap of all the ids that satisfy th data string from the
	 * root to the current node.
	 * @param dataString
	 * @param type
	 * @param score
	 * @param id
	 */
	public void add(String dataString,String type,double score,String id) {
		dataString = dataString.toLowerCase();
		String[] tokens = dataString.split(" ");
		
		for(String token:tokens) {
			addToken(token,type, score, id);
		}
	}
	
	public void delete(String id) {
		root.delete(id.toLowerCase());
	}
	
	private void addToken(String token,String type,double score,String id) {
		int i = 0;
		TrieNode current = root;
		for(i = 0;i<token.length();i++) {
			TrieNode next = current.get(token.charAt(i));
			if(next == null) {
				current.put(token.charAt(i),type, score, id);
				next = current.get(token.charAt(i));
			} else {
				next.add(type,score, id);
			}
			current = next;
		}
	}
	
	/** the wquery command. it returns a list of ids which are the result
	 * @param numOfResult
	 * @param numOfBoosts
	 * @param typeBoosts
	 * @param idBoosts
	 * @param searchString
	 * @return
	 */
	public List<String> wquery(int numOfResult,int numOfBoosts,Map<String,Double> typeBoosts,Map<String,Double> idBoosts,String searchString) {
		if(numOfBoosts == 0)
			return query(numOfResult, searchString);
		
		int num = numOfResult;
		searchString = searchString.toLowerCase();
		List<String> result = new ArrayList<String>();
		
		String[] tokens = searchString.split(" ");
		List<List<String>> tempResult = new ArrayList<List<String>>();
		int prevSize = 0;
		
		while(numOfResult > result.size()) {	// modify if there are not enough results.. return the results we have..
			for(String token:tokens) {
				tempResult.add(wquery_single(num, numOfBoosts,typeBoosts,idBoosts,token));
			}
			
			result = QuoraUtil.common(tempResult);
			if(result.size() == prevSize)
				break;
			else
				prevSize = result.size();
			num += numOfResult;
			tempResult.clear();
		}
		return result;
	}
	
	/**
	 * works for a single token of wquery
	 * @param numOfResult
	 * @param numOfBoosts
	 * @param typeBoosts
	 * @param idBoosts
	 * @param searchString
	 * @return
	 */
	public List<String> wquery_single(int numOfResult,int numOfBoosts,Map<String,Double> typeBoosts,Map<String,Double> idBoosts,String searchString) {
		// Very bad coding done here.. please please refactor and improve the design here
		if(numOfBoosts == 0)
			return query(numOfResult, searchString);
		String token = searchString.toLowerCase();
		int i = 0;
		TrieNode current = root;
		while(i<token.length()) {
			TrieNode next = current.get(token.charAt(i));
			if(next == null)
				return null;
			current = next;
			i++;
		}
		
		Map<String,Double> resultSet1,resultSet2,resultSet3;
		Heap h = new Heap();
		
		if(typeBoosts != null) {
			resultSet1 = current.wquery(numOfResult, typeBoosts, "type");
			for(String key:resultSet1.keySet()) {
				h.add("none", resultSet1.get(key), key);
			}
		}
		if(idBoosts != null) {
			resultSet2 = current.wquery(numOfResult, idBoosts, "id");
			for(String key:resultSet2.keySet()) {
				h.add("none", resultSet2.get(key), key);
			}
		}
		resultSet3 = current.wquery(numOfResult, null, "none");
		
		for(String key:resultSet3.keySet()) {
			h.add("none", resultSet3.get(key), key);
		}
		
		return h.topIds(numOfResult);
	}
	
	/** The query command. returns the list of ids that satisfy the query.
	 * @param numOfResults
	 * @param searchString
	 * @param arguments
	 * @return
	 */
	public List<String> query(int numOfResults,String searchString,Object... arguments) {
		int num = numOfResults;
		searchString = searchString.toLowerCase();
		List<String> result = new ArrayList<String>();
		
		String[] tokens = searchString.split(" ");
		List<List<String>> tempResult = new ArrayList<List<String>>();
		int prevSize = 0;
		
		while(numOfResults > result.size()) {	// modify if there are not enough results.. return the results we have..
			for(String token:tokens) {
				tempResult.add(topIds(num, token));
			}
			
			result = QuoraUtil.common(tempResult);
			if(result == null || result.size() == prevSize)
				break;
			else
				prevSize = result.size();
			num += numOfResults;
			tempResult.clear();
		}
		if(result.size() > numOfResults)
			return result.subList(0, numOfResults);
		return result;
	}
	
	public List<String> topIds(int numOfResult,String token) {
		token = token.toLowerCase();
		int i = 0;
		TrieNode current = root;
		while(i<token.length()) {
			TrieNode next = current.get(token.charAt(i));
			if(next == null)
				return null;
			current = next;
			i++;
		}
		
		return current.topIds(numOfResult);
	}
}
