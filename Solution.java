/*
 * CSE 374 Insertion Sort
 * Copyright 2021 Michael Glum
 */

class Solution {
	
     /**
     * Sorts an array using insertion sort
     * @param arr Array to sort
     * @param N Number of the transformation to return
     * @return The Nth transformation during the insertion sort process
     */
    public int[] insertionSort(int[] arr, int N)
    {
    	int val, pos, step = 0;
    	for (int i = 0; i < arr.length; i++) {
    		val = arr[i];
    		pos = i;
    		// Check if the selected value is greater than the number to the left of it
    		while (pos > 0 && val < arr[pos - 1]) {
    			// Move the selected value to the left by increments of 1 until it reaches a smaller number or the end of the array
    			pos--;
    			arr[pos + 1] = arr[pos];
    			arr[pos] = val;
    			step++;
    			// Return the Nth transformation
    			if (step == N) {
    				return arr;
    			}
    		}
    	}
    	// If sorting finished in under N transformations, return the sorted array
    	return arr;
    }
}