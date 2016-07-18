package quora.heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**A max heap which stores the max score user ids at the top of the heap. The insert and delete
 * operations are synchronized to avoid multiple threads modifying the same heap. 
 * 
 * @author Prasad
 *
 */
public class Heap {
	private List<Node> list;
	private Map<String,Integer> idPosition;
	
	public Heap() {
		list = new ArrayList<Node>();
		idPosition = new HashMap<String,Integer>();
		list.add(null);
	}
	
	public synchronized void delete(String uid) {
		if(idPosition.containsKey(uid)) {
			int position = idPosition.get(uid);
			idPosition.remove(uid);
			delete(position);
		}
	}
	
	private String delete(int index) {
		String data = list.get(index).toString();
		swap(index,list.size()-1);
		list.remove(list.size()-1);
		maxHeapify(index);
		return data;
	}
	
	public synchronized void add(String type,double score,String uniqueId) {
		Node n = new Node(type,score,uniqueId);
		int len = list.size();
		list.add(n);
		idPosition.put(n.toString(), list.size()-1);
		balanceHeap(len);
	}
	
	public Map<String,Double> topIdsWithTypeBoost(int n,Map<String,Double> boost) {
		Map<String,Double> boostedTypes = new HashMap<String,Double>();
		
		List<Node> tempList = new ArrayList<Node>();
		Map<String,Integer> tempIdPosition = new HashMap<String,Integer>();
		
		synchronized(this) {
			tempList.addAll(list);
			tempIdPosition.putAll(idPosition);
			
			while(n>0) {
				try {
					if(boost != null) {
						if(boost.containsKey(list.get(1).type())) {
							double boostedScore = list.get(1).score() * boost.get(list.get(1).type());
							boostedTypes.put(delete(1),boostedScore);
							n--;
						} else {
							delete(1);
						}
					} else {
						double boostedScore = list.get(1).score();
						boostedTypes.put(delete(1),boostedScore);
					}
				} catch(Exception e) {
					break;
				}
			}
			
			list.clear();
			list.addAll(tempList);
			idPosition.clear();
			idPosition.putAll(tempIdPosition);
		}
		
		return boostedTypes;
	}
	
	public Map<String,Double> topIdsWithIdBoost(int n,Map<String,Double> idBoost) {
		Map<String,Double> boostedIds = new HashMap<String,Double>();
		
		for(String id:idBoost.keySet()) {
			if(idPosition.containsKey(id)) {
				int position = idPosition.get(id);
				boostedIds.put(id, list.get(position).score() * idBoost.get(id));
			}
		}
		
		return boostedIds;
	}
	
	public List<String> topIds(int n) {
		
		List<String> topIds = new ArrayList<String>();
		
		List<Node> tempList = new ArrayList<Node>();
		Map<String,Integer> tempIdPosition = new HashMap<String,Integer>();
		
		synchronized(this) {
			tempList.addAll(list);
			tempIdPosition.putAll(idPosition);
			
			while(n>0) {
				try {
					topIds.add(delete(1));
				} catch(Exception e) {
					break;
				}
				n--;
			}
			
			list.clear();
			list.addAll(tempList);
			idPosition.clear();
			idPosition.putAll(tempIdPosition);
		}
		
		return topIds;
	}
	
	public int size() {
		return list.size() - 1;
	}
	
	private int left(int i) {
		return 2*i;
	}
	
	private int right(int i) {
		return 2*i+1;
	}
	
	private void balanceHeap(int i) {
		if(i >= 1) {
			maxHeapify(i);
			i = (int) Math.floor((i)/2);
			balanceHeap(i);
		}
	}
	
	/** The left and right sub heap satisfy the heap property but the root does not
	 * Max heapify converts it to max heap
	 * @param i
	 * @param n
	 */
	private void maxHeapify(int i) {
		int largest = i;
		int left = left(i);
		int right = right(i);
		
		// list.get(left).equals(list.get(i)) && list.get(left).age < list.get(i).age
		if(left<list.size() && (list.get(left).lessThanOrEquals(list.get(i)))) {
			largest = i;
		} else {
			if(left<list.size())
				largest = left;
		}
		
		if(right<list.size() && !(list.get(right).lessThanOrEquals(list.get(largest)))) {
			largest = right;
		}
		
		if(i!=largest) {
			swap(i,largest);
			maxHeapify(largest);
		}
	}
	
	private void swap(int i,int j) {
		Node temp = list.get(i);
		list.set(i, list.get(j));
		idPosition.put(list.get(j).toString(), i);
		list.set(j, temp);
		idPosition.put(temp.toString(), j);
	}
	
	public static void main(String[] args) {
		Heap h = new Heap();
		//List<String> uids = new ArrayList<String>();
		h.add("user",1.0, "u1");
		h.add("user",1.0, "u2");
	}
}
