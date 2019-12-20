import java.util.ArrayList;
import java.util.Collections;

public class SelectionSort {

    // Time complexity: O(n^2) - Space complexity: O(1)
    public static <T extends Comparable<T>> void selectionSort(ArrayList<T> arr) {
        for (int i = 0; i < arr.size() - 1; i++) {
            int iMin = i;
            for (int j = i + 1; j < arr.size(); j++) {
                iMin = (arr.get(j).compareTo(arr.get(iMin)) < 0) ? j : iMin;
            }
            Collections.swap(arr, i, iMin);
        }
    }
}