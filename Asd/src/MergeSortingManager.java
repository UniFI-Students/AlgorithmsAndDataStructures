import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSortingManager implements ISortingManager {
	@Override
	public <T> void sort(List<T> collection, Comparator<T> comparator) {
		mergeSort(collection, comparator, 0, collection.size() - 1);
	}

	private <T> void mergeSort(List<T> collection, Comparator<T> comparator, int L, int R) {
		if (L >= R)
			return;
		int m = (L + R) >> 1;
		mergeSort(collection, comparator, L, m);
		mergeSort(collection, comparator, m + 1, R);
		merge(collection, comparator, L, m, m + 1, R);
	}

	private <T> void merge(List<T> collection, Comparator<T> comparator, int LBegin, int LEnd, int RBegin, int REnd) {
		int n = REnd - LBegin + 1;
		ArrayList<T> mergedArray = new ArrayList<T>(n);

		int i = LBegin;
		int j = RBegin;

		while (i <= LEnd && j <= REnd) {
			int comparationResult = comparator.compare(collection.get(i), collection.get(j));

			if (comparationResult > 0)
				mergedArray.add(collection.get(j++));
			else
				mergedArray.add(collection.get(i++));
		}

		if (i <= LEnd)
			for (int index = i; index <= LEnd; ++index)
				mergedArray.add(collection.get(index));
		else
			for (int index = j; index <= REnd; ++index)
				mergedArray.add(collection.get(index));

		for (i = 0; i < mergedArray.size(); ++i) {
			collection.set(i + LBegin, mergedArray.get(i));
		}
	}
}
