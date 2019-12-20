import java.util.ArrayList;
import java.util.Collections;

public class QuickSort {

    // Time complexity: average O(n*log(n)) and worst O(n^2) - Space complexity: O(log(n))
    public static <T extends Comparable<T>> void quickSort(ArrayList<T> arr) {
        quickSort(arr, 0, arr.size() - 1);
    }

    private static <T extends Comparable<T>> void quickSort(ArrayList<T> arr, int start, int end) {
        if (start < end) {
            int pivotIndex = partition(arr, start, end);
            quickSort(arr, start, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, end);
        }
    }

    private static <T extends Comparable<T>> int partition(ArrayList<T> arr, int start, int end) {
        int pivotIndex = pickPivotIndex(start, end);
        T pivot = arr.get(pivotIndex);
        Collections.swap(arr, pivotIndex, end);
        int index = start;
        for (int i = start; i < end; i++) {
            if (arr.get(i).compareTo(pivot) < 0) {
                Collections.swap(arr, i, index);
                index++;
            }
        }
        Collections.swap(arr, index, end);
        return index;
    }

    private static int pickPivotIndex(int start, int end) {
        return (int) (start + (end - start + 1) * Math.random());
    }
}
