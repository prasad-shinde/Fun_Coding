package set;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Set<T> {
	private Map<T,T> hash;
	
	public Set() {
		hash = new HashMap<T,T>();
	}
	
	public void add(T data) {
		if(!hash.containsKey(data)) {
			hash.put(data,data);
		}
	}
	
	public T remove(T data) {
		return hash.remove(data);
	}
	
	public Iterator<T> iterator() {
		return hash.values().iterator();
	}
	
	public boolean contains(T data) {
		return hash.containsKey(data);
	}
	
	public Object[] toArray() {
		Collection<T> c = hash.values();
		return c.toArray();
	}
	
	public int size() {
		return hash.size();
	}
	
	public void print() {
		Collection<T> c = hash.values();
		Iterator<T> it = c.iterator();
		while(it.hasNext()) {
			System.out.print(it.next() + " ");
		}
	}
	
	public static void main(String[] args) {
		Set<Integer> s = new Set<Integer>();
		s.add(1);
		s.add(2);
		s.add(3);
		s.add(4);
		s.add(2);
		
		s.print();
		System.out.print(s.toArray());
	}
}
