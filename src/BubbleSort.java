import java.util.ArrayList;
import java.util.Collections;

public class BubbleSort {

    // Time complexity: average O(n^2) and best O(n) - Space complexity: O(1)
    public static <T extends Comparable<T>> void bubbleSort(ArrayList<T> arr) {
        for (int i = 0; i < arr.size() - 1; i++) {
            for (int j = 1; j < arr.size(); j++) {
                if (arr.get(j).compareTo(arr.get(j - 1)) < 0) {
                    Collections.swap(arr, j, j-1);
                }
            }
        }
    }
}