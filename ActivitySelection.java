/* 
 * Copyright 2021 Michael Glum
 * 
 * Given n activities with their start and finish times.
 * This program employs a greedy algorithm to select the maximum number 
 * of activities that can be performed by a single person, assuming that
 * a person can only work on a single activity at a time.
 */

import java.util.*;
public class ActivitySelection {

	/**
     * Simple main function to run the findMaxActivities helper method
     * and print the results using the printArr helper method.
     */
	public static void main(String[] args) {
		int[] startTime = {1, 3, 0, 5, 8, 5};
		int[] finishTime = {2, 4, 6, 7, 9, 9};
		int[] activities = findMaxActivities(startTime, finishTime);
		printArr(activities);
	}
	
	/**
     * Helper method to print and format an array
     * @param arr Array to print
     */
	static void printArr(int[] arr) {
		int i;
		for (i = 0; i < arr.length - 1; i++) {
			System.out.print(arr[i] + ", ");
		}
		System.out.println(arr[i]);
	}
	
	/**
     * Given n activities with their start and finish times.
     * This helper method employs a greedy algorithm to select the maximum 
     * number of activities that can be performed by a single person, assuming
     * that a person can only work on a single activity at a time
     * @param startTime Array of n start times
     * @param finishTime Array of n finish times
     * @return An array of the optimal activities to be performed
     */
    static int[] findMaxActivities(int[] startTime, int[] finishTime) {
    	// Array list to keep track of the optimal activities
    	ArrayList<Integer> activities = new ArrayList<Integer>();
    	// Array to keep track of which activities have already been included
    	// in the list of optimal activities
        int[] included = new int[startTime.length];
        int len = finishTime.length, min, finishT = 0, elem = 0;
        // For each activity, pick the next activity whose finish time is least
        // among the remaining activities and the start time is more than or
        // equal to the finish time of the previously selected activity
        for (int i = 0; i < len; i++) {
        	min = Integer.MAX_VALUE;
        	for (int j = 0; j < len; j++) {
        		// Make sure this activity has not already been included
        		if (included[j] != 1) {
        			if (finishTime[j] < min) {
        				if (startTime[j] >= finishT) {
        					elem = j;
        					min = finishTime[j];
        				}
        			}
        		}
        	}
        	// If the selected activity has not been included already, add
        	// it to the list of optimal activities
        	if (included[elem] != 1) {
        		activities.add(elem);
        		// Add to the list of included activities
        		included[elem] = 1;
        		// The finish time of this activity is now the "finish time
        		// of the previously selected activity"
        		finishT = min;
        	}
        }
        // Return the list of optimal activities
        return toIntArray(activities);
    }
    
    /**
     * Helper method to convert an array list of integers to an
     * array of integers
     * @param arrList An array list of integers
     * @return The converted array of integers
     */
    static int[] toIntArray(ArrayList<Integer> arrList) {
    	int[] intArr = new int[arrList.size()];
    	for (int i = 0; i < arrList.size(); i++) {
    		intArr[i] = arrList.get(i);
    	}
    	return intArr;
    }
}
