package ex03;

import java.util.Comparator;

public class OptimizedBubbleSort {
	
	

	public <T> int sort(T[] array, Comparator<T> comparator, boolean optimized) {
		// TODO Auto-generated method stub
		
		if (array == null) {
            return 0;
        }

		if (optimized) {
			return op_bubble_sort(array, comparator);
		} else {
			return bubble_sort(array, comparator);
		}
        
	}
	
	private <T> int op_bubble_sort(T[] array, Comparator<T> comparator) {
		boolean swapped = true;
        int comparisons = 0;
        int lastSwap = array.length - 1;

        while (swapped) {

            swapped = false;
            int lastI = 0;

            for (int i = 0; i < lastSwap; i++) {
                int j = i + 1;

                if (comparator.compare(array[i], array[j]) > 0) {
                    T tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;

                    swapped = true;
                    lastI = i;
                }
                comparisons++;
            }
            lastSwap = lastI;
        }
        
        return comparisons;
	}
	
	private <T> int bubble_sort(T[] array, Comparator<T> comparator) {
		boolean swapped = true;
        int comparisons = 0;

        while (swapped) {

            swapped = false;

            for (int i = 0; i < array.length - 1; i++) {
                int j = i + 1;

                if (comparator.compare(array[i], array[j]) > 0) {
                    T tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;

                    swapped = true;
                }
                comparisons++;
            }
        }
        
        return comparisons;
	}

}
