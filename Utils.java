
import java.util.ArrayList;

/**
 * Utility class for quick sort.
 * @author Dennis Yiaile
 */
public class Utils {

    /**
     * Returns the middle element, as specified by compareTo.
     * @param <E> - generic type
     * @param l - element in the left
     * @param r - element in the right
     * @param m - element in the middle
     * @return - return middle element 
     */
    public static <E extends Comparable<E>> E median(E l, E r, E m) {
        if ((l.compareTo(m) <= 0 && l.compareTo(r) >= 0) || 
            (l.compareTo(m) >= 0 && l.compareTo(r) <= 0)) {
            return l;
        } else if ((r.compareTo(m) >= 0 && r.compareTo(l) <= 0)
            || r.compareTo(m) <= 0 && r.compareTo(l) >= 0) {
            return r;
        } else {
            return m;
        }
    }

    /**
     * Implements the median-of-tree pivot selection. 
     * @param <E> - generic type
     * @param arr   the array to select three elements from 
     * @param lo    the location of the first element
     * @param hi    the location of the second element 
     * @return      the index of the meidan element (lo, hi, or mid)
     */
    public static <E extends Comparable<E>> int 
        medianOfThree(ArrayList<E> arr, int lo, int hi) {
        int mid = (hi + lo) / 2;
        E l = arr.get(lo);
        E m = arr.get(mid);
        E r = arr.get(hi);
        E median = median(l, r, m);

        if (median.equals(l)) {
            return lo;
        } else if (median.equals(r)) {
            return hi;
        } else {
            return mid;
        }
    }

    /**
     * Swaps the elements in locations i and j in arr.
     * @param <E> - generic type
     * @param arr - array to swap element
     * @param i - left element
     * @param j - right element 
     */
    public static <E extends Comparable<E>> void 
        swap(ArrayList<E> arr, int i, int j) {
        E temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

    /**
     * Given an arr, find a pivot using the medianOfThree method.
     * Follow the quicksort algorithm using left High and rightLow markers.
     * @param <E> - generic type
     * @param arr - arrayList to pivot
     * @param lo - left element
     * @param hi - element at the pivot
     * @return int - pivot position after partition
     */
    public  static <E extends Comparable<E>> int 
        partitionPivot(ArrayList<E> arr, int lo, int hi) {
        int pivot = medianOfThree(arr, lo, hi);
        swap(arr, hi, pivot);
        E pivotVal = arr.get(hi);
        int leftHigh = lo;
        int rightLow = hi - 1;

        while (rightLow >= leftHigh) {
            while (leftHigh <= rightLow 
                && arr.get(leftHigh).compareTo(pivotVal) <= 0) {
                leftHigh++;
            }

            while (rightLow >= leftHigh && 
                arr.get(rightLow).compareTo(pivotVal) > 0) {
                rightLow--;
            }
            if (leftHigh < rightLow) {
                swap(arr, leftHigh, rightLow);
            }
        }
        swap(arr, leftHigh, hi);
        return leftHigh;

    }

    /**
     * Sort an arrayList using quickSort helper method.
     * @param <E> - generic type
     * @param arr - an ArrayList to sort
     * @param left - element on the left
     * @param right - element on the right
     */
    public static <E extends Comparable<E>> void 
        quickSort(ArrayList<E> arr, int left, int right) {
        if (left < right) {
            int pivot = partitionPivot(arr, left, right);
            quickSort(arr, left, pivot - 1);
            quickSort(arr, pivot + 1, right);
        }
    }

    /**
     * Quick sorted main method.
     * @param <E> - generic type
     * @param arr - arrayList to sort
     */
    public static <E extends Comparable<E>> void quickSort(ArrayList<E> arr) {
        quickSort(arr, 0, arr.size() - 1);
    }
}