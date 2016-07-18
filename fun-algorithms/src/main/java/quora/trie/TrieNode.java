package quora.trie;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import quora.heap.Heap;

public class TrieNode {
	private char key;
	private Heap heapOfIds;
	private Set<String> set;
	private Map<Character,TrieNode> map;
	
	public TrieNode() {
		heapOfIds = null;
		set = new HashSet<String>();
		map = new HashMap<Character,TrieNode>();
	}
	
	public TrieNode(char ch,String type,double score,String id) {
		key = ch;
		heapOfIds = new Heap();
		set = new HashSet<String>();
		set.add(id);
		heapOfIds.add(type,score, id);
		map = new HashMap<Character,TrieNode>();
	}
	
	public List<String> topIds(int n) {
		return heapOfIds.topIds(n);
	}
	
	public Map<String,Double> wquery(int n,Map<String,Double> boost,String boostType) {
		if(boostType.equalsIgnoreCase("type")) {
			return heapOfIds.topIdsWithTypeBoost(n, boost);
		} else if(boostType.equalsIgnoreCase("id")) {
			return heapOfIds.topIdsWithIdBoost(n, boost);
		} else {
			return heapOfIds.topIdsWithTypeBoost(n, null);
		}
	}
	
	public void add(String type,double score,String id) {
		if(!set.contains(id)) {		// avoids same id being inserted to the same node and heap
			heapOfIds.add(type,score, id);
			set.add(id);
		}
	}
	
	public void delete(String id) {
		if(set.contains(id)) {
			// delete it from heap
			if(heapOfIds != null)
				heapOfIds.delete(id);
			// ask all the childrens of trie to delete the same key
			Iterator<TrieNode> it = map.values().iterator();
			while(it.hasNext()) {
				it.next().delete(id);
			}
			set.remove(id);
		}
	}
	
	public void put(char ch,String type,double score,String id) {
		map.put(ch, new TrieNode(ch,type,score,id));
		set.add(id);
	}
	
	public TrieNode get(char ch) {
		return map.get(ch);
	}
}
