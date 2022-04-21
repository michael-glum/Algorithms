/*
 * CSE 374 Merge Sort
 * Copyright 2021 Michael Glum
 */

import java.util.Arrays;

public class Solution {
    /**
     * Uses merge sort to sort an array in both ascending and descending order
     * by calling helper methods. 
     * @param arr Array to sort
     * @return The array containing both versions of the sorted array
     */
    public static int[][] mergeSort(int[] L) {
        int[][] sortedArrays = new int[2][L.length];
        boolean isAscending = true;
        // Sort in ascending order
        sortedArrays[0] = recursiveMergeSort(L, isAscending);
        // Sort in descending order
        sortedArrays[1] = recursiveMergeSort(L, !isAscending);
        // Return the sorted arrays
        return sortedArrays;
    }
    /**
     * Recursively breaks down an array into smaller arrays until the array
     * has a size of one. Then calls the merge helper method to rebuild the
     * array in sorted form.
     * @param arr Array to break down
     * @param isAscending Boolean determining if the array is to be sorted in
     * ascending or descending order.
     * @return Recursively returns the merged and sorted version of each broken
     * down array until there is just one sorted array to be returned to the
     * original call.
     */
    public static int[] recursiveMergeSort(int[] L, boolean isAscending) {
        // Get the midpoint of the given array
        int mid = L.length / 2;
        int[] leftArr;
        int[] rightArr;
        // If the array has a length of 1, it cannot be broken down anymore,
        // so return the array
        if (mid < 1) {
            return L;
        // If the array has a length of 2 or 3, ensure that the array is split
        // into two sub-arrays at the index 1
        } else if (mid == 1) {
            leftArr = Arrays.copyOfRange(L, 0, mid);
            rightArr = Arrays.copyOfRange(L, mid, L.length);
        // Break the array into two sub-arrays at the midpoint
        } else {
            leftArr = Arrays.copyOfRange(L, 0, mid + 1);
            rightArr = Arrays.copyOfRange(L, mid + 1, L.length);
        }
        // Recursively break down each sub-array until they only contain 1
        // element
        leftArr = recursiveMergeSort(leftArr, isAscending);
        rightArr = recursiveMergeSort(rightArr, isAscending);
        // Merge the sub-arrays in a sorted order
        return merge(leftArr, rightArr, isAscending);
    }
	
    /**
     * Merges two sub-arrays into a single sorted array.
     * @param leftArr The left half of the array to merge.
     * @param rightArr The right half of the array to merge.
     * @param isAscending Boolean determining if the array is to be sorted in
     * ascending or descending order.
     * @return Merged and sorted array consisting of the two sub-arrays.
     */
    public static int[] merge(int[] leftArr, int[] rightArr,
    	boolean isAscending) {
        // Create a new array that is the size of the two sub-arrays put
        // together
        int[] mergedArr = new int[leftArr.length + rightArr.length];
        int iL = 0, iR = 0;
        // Starting at the leftmost element in each array, check for the
        // minimum (if isAscending) or maximum (if !isAscending) value between
        // the two sub-arrays, add it to the merged array, and move on to the
        // next element until one of the sub-arrays is emptied.
        while (iL < leftArr.length && iR < rightArr.length) {
            if (leftArr[iL] < rightArr[iR] == isAscending) {
                mergedArr[iL + iR] = leftArr[iL];
                iL++;
            } else {
                mergedArr[iL + iR] = rightArr[iR];
                iR++;
            }
        }
        // Once one of the sub-arrays is emptied, fill the rest of the
        // merged array with the leftover values in the remaining
        // sub-array (these are already sorted).
        while (iL < leftArr.length) {
            mergedArr[iL + iR] = leftArr[iL];
            iL++;
        }
        while (iR < rightArr.length) {
            mergedArr[iL + iR] = rightArr[iR];
            iR++;
            }
        // Return the sorted array
        return mergedArr;
    }
}