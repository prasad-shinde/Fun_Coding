package schedulling;

import java.util.HashMap;
import java.util.Map;
import queue.Queue;

public class LeastRecentlyUsed {
	private Map<Integer,String> lruMap;
	private Queue<Page> cache;
	private int count;
	private int size;
	
	public LeastRecentlyUsed() {
		lruMap = new HashMap<Integer,String>();
		cache = new Queue<Page>();
		size = 4;
		count = 0;
	}
	
	private class Page {
		public int pageNo;
		public String data;
		
		public Page(int num,String value) {
			pageNo = num;
			data = value;
		}
		
		public int pageNumber() {
			return pageNo;
		}
		
		public String data() {
			return data;
		}
		
		public String toString() {
			return pageNo + data;
		}
		
		public Page[] createPages(int[] arr) {
			int index = 0;
			Page[] pages = new Page[arr.length];
			for(int i:arr) {
				pages[index++] = new Page(i,i+"");
			}
			return pages;
		}
	}
	
	public void add(Page p) {
		if(lruMap.containsKey(p.pageNumber())) {
			cache.moveToFront(p);
		}
		else {
			if(count < size) {
				lruMap.put(p.pageNumber(), p.data());
				cache.enqueue(p);
				count++;
			}
			else {
				lruMap.put(p.pageNumber(), p.data());
				cache.dequeue();
				cache.enqueue(p);
				count++;
			}
		}
	}
	
	public Page lru() {
		if(count>0) {
			count--;
			return cache.dequeue();
		}
		else
			throw new IndexOutOfBoundsException("cache is already empty!! cannot dequeue");
	}
	
	public String toString() {
		return cache.toString();
	}
	
	public void addBulk(Page[] pages) {
		for(Page page:pages) {
			add(page);
		}
	}
	
	public static void main(String[] args) {
		LeastRecentlyUsed lru = new LeastRecentlyUsed();
		int[] arr = {1,2,3,4,5,6,7};
		Page p = lru.new Page(1,"");
		Page[] pages = p.createPages(arr);
		
		lru.addBulk(pages);
		
		System.out.print(lru.toString());
		//lru.add(lru.new Page(4,"4"));
		System.out.print("\n here");
		System.out.print(lru.toString());
	}
	
}
