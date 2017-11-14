package ex02;

/*# Exercise 02

	Write a class called `RingArrayQueue` that implements `MyQueue`. 
	Internally, it should be similar to the implementation of `ArrayQueue`, 
	but with a fundamental performance improvement.
	When by adding many elements the `tail` index reaches the end of the internal array,
 	**instead of** shifting elements to the left or copying over to a new larger array,
	the `tail` should start back from `0`, unless of course `head==0`.
 
	The idea is to reuse the empty spaces before `head` when `head>0`.
	Note, when `head==0`, or when `tail` increases so much that it reaches `head`, then it would
	mean that the array is completely full, and you need to copy over to a new internal array.
 
	Solutions to this exercise can be found under the `org.pg4200.exercise.ex02` package. 
*/

import org.pg4200.datastructure.queue.*;

public class RingArrayQueue<T> implements MyQueue<T> {
	
	Object[] data;
	
	int head; int tail;
	
	public RingArrayQueue() {
		this(10);
	}
	
	public RingArrayQueue(int cap) {
		head = -1;
		tail = -1;
		data = new Object[cap];
	}

	@Override
	public void enqueue(T value) {
		
		if (isEmpty()) {
			head = 0;
			tail = 0;
		} else if(size() == data.length) {
			expand( Math.min(data.length * 2, 400) );
			tail++;
		} else {
			tail++;
			if (tail == data.length) tail = 0;
		}
		
		data[tail] = value;
		
	}

	@Override
	public T dequeue() {
		
		if(isEmpty()){
            throw new RuntimeException();
        }
		
		@SuppressWarnings("unchecked")
		T item = (T) data[head];
		
		if (size() == 1) {
			head = -1;
			tail = -1;
		} else {
			head++;
			if (head == data.length) head = 0;
		}
		
		return item;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T peek() {

		if(isEmpty()) {
			throw new RuntimeException();
		}
		
		return (T) data[head];
	}

	@Override
	public int size() {
		
		if (head < 0) return 0;
		if ( tail >= head ) return tail - head + 1;
		if ( tail < head) return (data.length - head) + tail + 1;
		return 0;
	}
	
	// FIX EXPAND()
	private void expand(int ammount) {
		Object[] tmp = new Object[data.length + ammount];
		
		int size = size();
		for (int i = 0; i < size; i++) {
			tmp[i] = dequeue();
		}
		
		head = 0;
		tail = size() -1;
		data = tmp;
	}

}
