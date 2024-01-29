package CampApplication.Utilities;

import java.util.ArrayList;

import CampApplication.Camps.Camp;
/**
 * Sorting camp names based on name and date
 *
 * @author Group2
 * @version 23/11/2023
 * @since 01/11/2023
 */
public class CampMergeSort {

    /**
     * merge the camp list in alphabetical order
     * @param array pass in array list of variable camp type
     */
    public static void mergeSortAlphabet(Camp[] array) {
        if (array == null || array.length <= 1) {
            return; // Base case: the array is already sorted or empty
        }

        // Divide the array into two halves
        int mid = array.length / 2;
        Camp[] left = new Camp[mid];
        Camp[] right = new Camp[array.length - mid];
        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, array.length - mid);

        // Recursively sort the two halves
        mergeSortAlphabet(left);
        mergeSortAlphabet(right);

        // Merge the sorted halves
        mergeAlphabet(left, right, array);
    }

    
    private static void mergeAlphabet(Camp[] left, Camp[] right, Camp[] result) {
        int i = 0; // Index for the left array
        int j = 0; // Index for the right array
        int k = 0; // Index for the merged array

        while (i < left.length && j < right.length) {
            if (left[i].getCampName().compareTo(right[j].getCampName()) <= 0) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        // Copy any remaining elements from the left array
        while (i < left.length) {
            result[k++] = left[i++];
        }

        // Copy any remaining elements from the right array
        while (j < right.length) {
            result[k++] = right[j++];
        }
    }

    public static void mergeSortDate(Camp[] array) {
        if (array == null || array.length <= 1) {
            return; // Base case: the array is already sorted or empty
        }

        // Divide the array into two halves
        int mid = array.length / 2;
        Camp[] left = new Camp[mid];
        Camp[] right = new Camp[array.length - mid];
        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, array.length - mid);

        // Recursively sort the two halves
        mergeSortDate(left);
        mergeSortDate(right);

        // Merge the sorted halves
        mergeDate(left, right, array);
    }

    private static void mergeDate(Camp[] left, Camp[] right, Camp[] result) {
        int i = 0; // Index for the left array
        int j = 0; // Index for the right array
        int k = 0; // Index for the merged array

        while (i < left.length && j < right.length) {
            if (left[i].getCampStartingDate().compareTo(right[j].getCampStartingDate()) <= 0) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        // Copy any remaining elements from the left array
        while (i < left.length) {
            result[k++] = left[i++];
        }

        // Copy any remaining elements from the right array
        while (j < right.length) {
            result[k++] = right[j++];
        }
    }
}
