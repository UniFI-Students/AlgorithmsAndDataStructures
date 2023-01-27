import java.util.Comparator;
import java.util.List;

public interface ISortingManager {

	<T> void sort(List<T> collection, Comparator<T> comparator);

}