/*
 * Dynamic programming solution for finding the maximum sum of elements in
 * an array of integers, where adjacent elements cannot both be added to the
 * total.
 * Copyright 2021 Michael Glum
 */

import java.util.*;

public class DynamicProgramming {
	public static void main(String[] args) {
		int[] houses = {5,6,8,2,3,6,3,7,3,8,11,2,4,7,2,13,6,9,9,2};
		
		System.out.println(rob(houses));
	}
	/**
     * Method for finding the maximum sum that can be extracted from the array
     * of integers, given the constraint that two adjacent elements cannot both
     * be added to the total.
     * @param houses The array of integers to be summed
     * 
     * @return The maximum sum that can be produced
     */
    static int rob(int[] houses) {
    	int total = 0;
    	int lastVal, currVal, nextVal;
    	// Iterate through the array, checking the current value, at i,
    	// against the last value, at i - 1, and the next value, at i + 1
    	for (int i = 1; i < houses.length - 1; i++) {
    		currVal = houses[i];
    		nextVal = houses[i+1];
    		lastVal = houses[i-1];
    		if (currVal >= nextVal) {
    			if (currVal > lastVal) {
    				// If the element at i is larger than both of its
    				// neighboring elements, add it to the total
    				total += currVal;
    				// Set the value of the next element to 0, since it cannot
    				// be added to the sum
    				houses[i + 1] = 0;
    			}
    			// Set the value at i to 0
    			houses[i] = 0;
    		} else {
    			// If the value at i is less than the value at i + 1
    			// add the value at i - 1 to the array, which is either 0 or
    			// a positive integer, depending on if it was previously taken
    			// or is excluded do to its neighbor being taken
    			total += lastVal;
    			// Catch the element at the end of the array if it is greater
    			// the current value at houses.length - 2
    			if (i == houses.length - 2) {
    				total += houses[i + 1];
    			}
    		}
    	}
    	// Return the maximum sum
        return total;
    }
}
