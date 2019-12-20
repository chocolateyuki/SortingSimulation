import java.lang.reflect.Array;
import java.util.ArrayList;

public class MergeSort {

    // Time complexity: O(n*log(n)) - Space complexity: O(n)
    static void mergeSort(ArrayList<Integer> arr) {
        if (arr.size() <= 1) {
            return;
        }
        int mid = arr.size() / 2;
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();
        for (int i = 0; i < mid; i++) {
            left.add(arr.get(i));
        }
        for (int i = mid; i < arr.size(); i++) {
            right.add(arr.get(i));
        }

        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }

    static void merge(ArrayList<Integer> arr, ArrayList<Integer> left, ArrayList<Integer> right) {
        int j = 0;
        int k = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (j == left.size()) {
                arr.set(i, right.get(k++));
            } else if (k == right.size()) {
                arr.set(i, left.get(j++));
            } else if (left.get(j).compareTo(right.get(k)) < 0) {
                arr.set(i, left.get(j++));
            } else {
                arr.set(i, right.get(k++));
            }
        }
    }
}
