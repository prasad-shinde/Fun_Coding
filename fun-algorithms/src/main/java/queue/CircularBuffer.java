package queue;

import java.lang.reflect.Array;

public class CircularBuffer <T> {
	private T[] buffer;
	int start,end;
	int size;

	@SuppressWarnings("unchecked")
	public CircularBuffer(Class<T> c,int size) {
		buffer = (T[])Array.newInstance(c, size + 1);
		start = end = 0;
		this.size = size;
	}
	
	public boolean isEmpty() {
		return start == end;
	}
	
	public boolean isFull() {
		return (start == ((end + 1)%size));
	}
	
	public void write(T data) {
		if(isFull())
			throw new ArrayIndexOutOfBoundsException("Buffer full cannot write");
		buffer[end+1] = data;
		end = (end + 1) % size;
	}
	
	public T read() {
		if(isEmpty())
			throw new ArrayIndexOutOfBoundsException("Buffer empty cannot read");
		T value = buffer[start];
		start = (start + 1) % size;
		return value;
	}
	
	public void print() {
		int temp = start;
		System.out.println("Buffer contents : ");
		while(temp != end) {
			System.out.print(buffer[temp]);
			temp = (temp + 1) % size;
		}
	}
}
