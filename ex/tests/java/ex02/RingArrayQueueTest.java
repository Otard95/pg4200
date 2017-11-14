package ex02;

/*
 *  Write a `RingArrayQueueTest` that extends `MyQueueTestTemplate`. 
	If your implementation is correct, all tests should pass.
	Run the tests with code coverage enabled, and verify that all of the instructions in your
	code are covered. If not, add new tests to `RingArrayQueueTest`.
 
	Solutions to this exercise can be found under the `org.pg4200.exercise.ex02` package. 
 */

import org.pg4200.datastructure.queue.*;

public class RingArrayQueueTest extends MyQueueTestTemplate {

	@Override
	protected <T> MyQueue<T> getNewInstance(Class<T> klass) {
		// TODO Auto-generated method stub
		return new RingArrayQueue<>();
	}

}
