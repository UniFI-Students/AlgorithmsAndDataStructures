import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MergeSortringManagerTest {

	private ISortingManager sortingManager;

	@Before
	public void setUp() {
		sortingManager = new MergeSortingManager();
	}

	@After
	public void tearDown() {

	}

	@Test
	public void testSort() {
		IntegerWrapper first2 = new IntegerWrapper(2);
		IntegerWrapper second2 = new IntegerWrapper(2);
		List<IntegerWrapper> sortingArrayList = Arrays.asList(new IntegerWrapper(7), new IntegerWrapper(5), first2,
				new IntegerWrapper(1), new IntegerWrapper(8), new IntegerWrapper(9), new IntegerWrapper(104), second2,
				new IntegerWrapper(0), new IntegerWrapper(-5), new IntegerWrapper(85));

		sortingManager.sort(sortingArrayList, IntegerWrapper::compare);

		int i = -1;
		boolean isSorted = true;
		while (isSorted && ++i < sortingArrayList.size() - 1)
			isSorted = sortingArrayList.get(i).getValue() <= sortingArrayList.get(i + 1).getValue();

		assertTrue(isSorted);

		int first2IndexAct = sortingArrayList.indexOf(first2);
		int second2IndexAct = sortingArrayList.indexOf(second2);
		assertEquals(3, first2IndexAct);
		assertEquals(4, second2IndexAct);

	}

	public class IntegerWrapper {
		private int value;

		public static int compare(IntegerWrapper x, IntegerWrapper y) {
			return Integer.compare(x.value, y.value);
		}

		public IntegerWrapper(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

	}
}
