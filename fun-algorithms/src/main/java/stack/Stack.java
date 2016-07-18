package stack;

import java.lang.reflect.Array;

public class Stack<T> {
	private T[] stack;
	private int size;
	private int top;
	
	@SuppressWarnings("unchecked")
	public Stack(Class<T> c,int size) {
		//used to create a generic array
		stack = (T[])Array.newInstance(c, size);
		top = -1;
	}
	
	public boolean isFull() {
		return top == (size-1);
	}
	
	public boolean isEmpty() {
		return top == -1;
	}
	
	public T peek() {
		if(!isEmpty())
			return stack[top];
		else
			return null; 
	}
	
	public void push(T data) {
		if(!isFull())
			stack[++top] = data;
		else
			throw new IndexOutOfBoundsException("Stack is full! Can't push more elements!");
	}
	
	public T pop() {
		if(!isEmpty())
			return stack[top--];
		else
			throw new IndexOutOfBoundsException("Stack is empty! Can't pop more elements!");
	}
}
