import java.util.ArrayList;

/**
 * Created by Ayush Bandil on 21/10/2019.
 */
public class GenerateUnsortedList {

    public static ArrayList<Integer> getUnsortedArray(ArrayList<Integer> sortedArray, int degreeOfUnsortedness, int desiredLength) {
        int toSwapElementsCount = (degreeOfUnsortedness * desiredLength / 200);
        ArrayList<Integer> trimmedSortedList = getTrimmedList(sortedArray, desiredLength);

        ArrayList<Integer> trimmedSortedListCloned = (ArrayList<Integer>) trimmedSortedList.clone();
        for (int i = 0; i < toSwapElementsCount; i++) {
            int rand1Index = (int) (Math.random() * desiredLength);
            int rand2Index = (int) (Math.random() * desiredLength);

            int temp = trimmedSortedListCloned.get(rand2Index);
            trimmedSortedListCloned.set(rand2Index, trimmedSortedListCloned.get(rand1Index));
            trimmedSortedListCloned.set(rand1Index, temp);
        }
        return trimmedSortedListCloned;
    }

    private static ArrayList<Integer> getTrimmedList(ArrayList<Integer> sortedArray, int desiredLength) {
        int parentLen = sortedArray.size();
        if (desiredLength == parentLen) {
            return sortedArray;
        }

        int startIndex = (int) ((parentLen - desiredLength) * Math.random());
        ArrayList<Integer> trimmedList = new ArrayList<>();

        for (int i = 0; i < desiredLength; i++) {
            trimmedList.add(sortedArray.get(startIndex + i));
        }
        return trimmedList;
    }
}