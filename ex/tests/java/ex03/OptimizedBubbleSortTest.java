package ex03;

import java.util.Comparator;
import org.junit.Test;
import static org.junit.Assert.*;

public class OptimizedBubbleSortTest {
	
	private OptimizedBubbleSort sorter = new OptimizedBubbleSort();
	
	class StringComparator implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			// TODO Auto-generated method stub
			return o1.compareTo(o2);
		}
		
	}
	
	@Test
	public void TestAllreadySorted () {
		
		String[] arr = {"a", "b", "c", "d"};
		
		int c = sorter.sort(arr, new StringComparator(), false);
		
		assertEquals(3 ,c);
		assertEquals("a", arr[0]);
		assertEquals("b", arr[1]);
		assertEquals("c", arr[2]);
		assertEquals("d", arr[3]);
		
	}
	
	@Test
	public void TestInverted () {
		
		String[] arr = {"d", "c", "b", "a"};
		
		int c = sorter.sort(arr, new StringComparator(), false);
		
		assertEquals(12, c);
		assertEquals("a", arr[0]);
		assertEquals("b", arr[1]);
		assertEquals("c", arr[2]);
		assertEquals("d", arr[3]);
		
	}
	
	@Test
	public void TestBase () {
		
		String[] arr = {"d", "a", "c", "b"};
		
		sorter.sort(arr, new StringComparator(), false);
		
		assertEquals("a", arr[0]);
		assertEquals("b", arr[1]);
		assertEquals("c", arr[2]);
		assertEquals("d", arr[3]);
		
	}
	
	@Test
	public void TestOptimized () {
		
		String[] array = {"c", "b", "a", "d", "e", "f"};
        int optimized = sorter.sort(array, new StringComparator(), true);

        array = new String[]{"c", "b", "a", "d", "e", "f"};
        int base = sorter.sort(array, new StringComparator(), false);

        assertTrue(optimized < base);
        assertTrue(optimized < base/2);

        assertEquals("a", array[0]);
        assertEquals("b", array[1]);
        assertEquals("c", array[2]);
        assertEquals("d", array[3]);
        assertEquals("e", array[4]);
        assertEquals("f", array[5]);
		
	}
	
}
