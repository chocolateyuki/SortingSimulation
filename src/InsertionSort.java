import java.util.ArrayList;
import java.util.Collections;

public class InsertionSort {

    // Time complexity: average O(n^2) and best O(n) - Space complexity: O(1)
    public static <T extends Comparable<T>> void insertionSort(ArrayList<T> arr) {
        for (int i = 0; i < arr.size(); i++) {
            T temp = arr.get(i);
            int j = i;
            while (j > 0 && arr.get(j - 1).compareTo(temp) > 0) {
                arr.set(j, arr.get(j-1));
                j--;
            }
            arr.set(j, temp);
        }
    }
}