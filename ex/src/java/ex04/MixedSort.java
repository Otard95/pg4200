package ex04;

import org.pg4200.sorting.MySort;

public class MixedSort implements MySort {
	
	private final int bubbleThreshold;
	
	public MixedSort (int bubbleThreshold) {
		this.bubbleThreshold = bubbleThreshold;
	}
	
	@Override
	public <T extends Comparable<T>> void sort (T[] array) {
		
		if(array == null) return;
		
		if(array.length == 1) return;
		
		T[] buffer = (T[]) new Comparable[array.length];
		
		mergeSort(0, array.length - 1, array, buffer);
		
	}

	private <T extends Comparable<T>> void mergeSort (int low, int high, T[] array, T[] buffer) {
		
		if (high - low <= bubbleThreshold) {
			bubbleSort (low, high, array);
			return;
		}
		
		int middle = low + ((high - low) / 2);
		
		mergeSort(low, middle, array, buffer);
		mergeSort(middle+1, high, array, buffer);
		
		merge(low, middle, high, array, buffer);
		
	}
	
	private <T extends Comparable<T>> void merge (int low, int middle, int high, T[] array, T[] buffer) {
		
		for (int i = low; i < high; i++) {
			buffer[i] = array[i];
		}
		
		int i = low;
		int j = middle + 1;
		
		for (int k = low; k < high; k++) {
			if (i > middle) {
				// first half finished
				array[k] = buffer[j++];
			} else if (j > high) {
				// second half finished
				array[k] = buffer[i++];
			} else if(buffer[i].compareTo(buffer[j]) < 0) {
				// i less then j
				array[k] = buffer[i++];
			} else {
				// j less then i
				array[k] = buffer[j++];
			}
		}
		
	}
	
	
	private <T extends Comparable<T>> void bubbleSort (int low, int high, T[] array) {
		
		boolean swapped = true;
		
		while (swapped) {
			swapped = false;
			
			for (int i = low; i < high; i++) {
				if (array[i].compareTo(array[i+1]) > 0) {
					T temp = array[i];
					array[i] = array[i+1];
					array[i+1] = temp;
					swapped = true;
				}
			}
		}
		
	}

}
