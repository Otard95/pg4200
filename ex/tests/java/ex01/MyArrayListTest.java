package ex01;

/*	Write a test class called `MyArrayListTest` that extends `DeleteContainerTestTemplate`,
	where the instance of `MyArrayList` is initialized with capacity of 1 
	(i.e., the size for the internal array).
	If your implementation of `MyArrayList#add` is correct, then all tests should pass.
	
	
	Solutions to this exercise can be found under the `org.pg4200.exercise.ex01` package.
*/

import org.pg4200.datastructure.delete.*;

import ex01.MyArrayList;

public class MyArrayListTest extends DeleteContainerTestTemplate {

	@Override
	protected <T> DeleteContainer<T> getNewInstance(Class<T> klass) {
		// TODO Auto-generated method stub
		return new MyArrayList<>(1);
	}

}
