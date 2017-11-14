package ex04;

import org.pg4200.sorting.*;

public class MixedSortTest extends SortTestTemplate {

	@Override
	protected MySort getInstance() {
		// TODO Auto-generated method stub
		return new MixedSort(5);
	}

}
