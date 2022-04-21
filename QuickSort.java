/*
 * CSE 374 QuickSort
 * Copyright 2021 Michael Glum
 */
import java.util.*;

public class QuickSort {
	/**
     * Uses quick sort to sort an array in both ascending and descending order
     * by calling helper methods. 
     * @param L Array to sort
     * @return The array containing both versions of the sorted array
     */
	public int[][] sort(int[] L) {
		int n = L.length;
		int[][] sortedArrays = {L, Arrays.copyOf(L, n)};
		boolean isAscending = true;
		// Sort in ascending order
		quickSort(sortedArrays[0], 0, n, isAscending);
		// Sort in descending order
		quickSort(sortedArrays[1], 0, n, !isAscending);
		// Return the sorted arrays
		return sortedArrays;
	}
	
	/**
     * Partitions the elements of the array around the pivot point, p, based on
     * whether or not they are greater than or equal to the pivot element, and
     * depending on if the boolean, isAscending, (determining if the array is
     * to be sorted in ascending or descending order) is true.
     * @param L Array to sort
     * @param p Index of the array to pivot around
     * @param q End index of the current portion of the array to be sorted
     * @param isAscending Boolean determining if the array is to be sorted in
     * ascending or descending order.
     * 
     * @return The index position separating the partitioned sub arrays.
     */
	public int partition(int[] L, int p, int q, boolean isAscending) {
		int val = L[p];
		int i = p;
		int temp;
		// Starting with the end element of the subsection of the array to be
		// sorted, if an element to the right of the pivot is lesser (if
		// isAscending) or greater (if !isAscending) than the pivot, partition
		// that element around the pivot point, by switching its position with
		// some element on the left side of the array
		for (int j = p + 1; j < q; j++) {
			if ((L[j] <= val) == isAscending) {
				i++;
				temp = L[i];
				L[i] = L[j];
				L[j] = temp;
			}
		}
		temp = L[p];
		L[p] = L[i];
		L[i] = temp;
		return i;
	}
	
	/**
     * Recursively sorts an array by calling the partition helper method in
     * in order to partition the array around a pivot point
     * @param L Array to sort
     * @param p Index of the array to pivot around
     * @param q End index of the current portion of the array to be sorted
     * @param isAscending Boolean determining if the array is to be sorted in
     * ascending or descending order.
     */
	public void quickSort(int[] L, int p, int q, boolean isAscending) {
		// Until sorting is finished
		if (p < q) {
			// Partition the current subsection of the array
			int x = partition(L, p, q, isAscending);
			// Recursively sort the partitioned sub arrays
			quickSort(L, p, x, isAscending);
			quickSort(L, x + 1, q, isAscending);
		}
	}
}
