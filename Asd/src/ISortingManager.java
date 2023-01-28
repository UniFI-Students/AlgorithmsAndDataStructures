import java.util.Comparator;
import java.util.List;

//Daniil Radchanka 7079901
//Anastasia Moskalenko 7015595
public interface ISortingManager {

	<T> void sort(List<T> collection, Comparator<T> comparator);

}