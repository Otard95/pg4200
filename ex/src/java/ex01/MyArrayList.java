package ex01;

/*
 *# Exercise 01

	Write a class called `MyArrayList` that extends `ArrayDeleteContainer`.
	
	Override the method `add(T value)` in a way that, if the internal array is full,
	then such array should be doubled in size before inserting the new element.
	
	Solutions to this exercise can be found under the `org.pg4200.exercise.ex01` package.

 */

import org.pg4200.datastructure.delete.*;

public class MyArrayList<T> extends ArrayDeleteContainer<T> {
	
	public MyArrayList() {
		super();
	}
	
	public MyArrayList(int cap) {
		super(cap);
	}
	
	@Override
	public void add(T value) {
		// Check if data[] is full
		if(data.length == size) {
			// data[] is full. double size
			Object[] temp = data.clone(); // Save data temporarily 
			// replace data with new Object[] with double size
			data = new Object[data.length * 2];
			// Move data back
			for (int i = 0; i < temp.length; i++) {
				data[i] = temp[i];
			}
		}
		
		data[size] = value;
		size++;
	}

}
