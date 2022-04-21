/*
 * CSE 374 HeapSort
 * Copyright 2021 Michael Glum
 */
import java.util.*;

public class Solution {
	/**
     * Uses heap sort to sort an array in both ascending and descending order
     * by calling helper methods. 
     * @param L Array to sort
     * @return The array containing both versions of the sorted array
     */
	public int[][] sort(int[] L) {
		int[][] sortedArrays = {L, Arrays.copyOf(L, L.length)};
		boolean isAscending = true;
		int heapEnd = L.length - 1;
		// Sort in ascending order
		heapSort(sortedArrays[0], heapEnd, true, isAscending);
		// Sort in descending order
		heapSort(sortedArrays[1], heapEnd, true, !isAscending);
		// Return the sorted arrays
		return sortedArrays;
	}
	
	/**
     * This method modifies a heap to ensure that it is either a min or max
     * heap (depending on the value of isAscending) by comparing the left and
     * right children to a parent node. 
     * @param L Array to sort
     * @param i Index of the parent node
     * @param heapEnd Index of the last element of the array
     * @param isAscending Boolean determining if the array is to be sorted in
     * ascending or descending order.
     */
	public void heapify(int[] L, int i, int heapEnd, boolean isAscending) {
		// Establish left child
		int l = i * 2 + 1;
		// Establish right child
		int r = i * 2 + 2;
		// Integer to contain the largest or smallest element compared,
		// depending on the value of isAscending
		int largeOrSmall;
		// Compare the left child to the parent node and set it as the largest
		// or smallest element, if applicable
		if (l <= heapEnd && (L[l] > L[i] == isAscending)) {
			largeOrSmall = l;
		} else {
			largeOrSmall = i;
		}
		// Compare the right child to the current largest or smallest element
		// and set it as such, if applicable
		if (r <= heapEnd && (L[r] > L[largeOrSmall] == isAscending)) {
			largeOrSmall = r;
		}
		// If the parent is not the largest or smallest element compared, swap
		// its position in the heap and call heapify again to ensure min or
		// max heap
		if (largeOrSmall != i) {
			int temp = L[i];
			L[i] = L[largeOrSmall];
			L[largeOrSmall] = temp;
			heapify(L, largeOrSmall, heapEnd, isAscending);
		}
	}
	
	/**
     * This method builds a min or max heap from an unsorted array by calling
     * heapify as a helper method for each parent node. 
     * @param L Array to sort
     * @param heapEnd Index of the last element of the array
     * @param isAscending Boolean determining if the array is to be sorted in
     * ascending or descending order.
     */
	public void buildHeap(int[] L, int heapEnd, boolean isAscending) {
		// Call the heapify helper method for each parent node
		for (int i = heapEnd / 2; i >= 0; i--) {
			heapify(L, i, heapEnd, isAscending);
		}
	}
	
	/**
     * This method performs heap sort on a given array by calling helper methods
     * and recursively removing elements from the heap until the heap is empty.
     * @param L Array to sort
     * @param heapEnd Index of the last element of the array
     * @param doBuildHeap Boolean that is true on the first heap sort call and
     * determines if the buildHeap helper method should be called.
     * @param isAscending Boolean determining if the array is to be sorted in
     * ascending or descending order.
     */
	public void heapSort(int[] L, int heapEnd, boolean doBuildHeap,
			boolean isAscending) {
		// On first call, build a min or max heap from the unsorted array,
		// depending on the value of isAscending
		if (doBuildHeap) {
			buildHeap(L, heapEnd, isAscending);
		}
		// Move the sorted element at L[0] to the end of the array and remove
		// it from the heap
		int temp = L[0];
		L[0] = L[heapEnd];
		L[heapEnd] = temp;
		heapEnd--;
		// Call heapify helper method to ensure min or max heap
		heapify(L, 0, heapEnd, isAscending);
		// Recursively call heapSort until the heap is empty
		if (heapEnd != 0) {
			heapSort(L, heapEnd, false, isAscending);
		}
	}
}