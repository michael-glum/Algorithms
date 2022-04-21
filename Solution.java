/*
 * Implementation of Dijkstra's Algorithm to find the cheapest total costs for
 * flights
 * Copyright 2021 Michael Glum
 */
import java.util.*;

class Solution {	
	/**
     * Finds the minimum child node of a given node in a digraph and sets its
     * total cost
     * @param currNode The node whose children will be compared
     * @param costs The array of costs for each node, the second element is 0
     * when a node has not been visited, and 1 when it has been visited
     * @param flights The edges between nodes and their weights
     * @param n The number of nodes
     * @return The index of the node with the minimum cost
     */
	public int extractMin(int currNode, double costs[][], int[][] flights,
			int n) {
		double minCost = Double.POSITIVE_INFINITY;
		int minNode = -1;
		// Find the children of the current node and find the minimum,
		// reducing their costs when applicable
		
		for (int i = 0; i < flights.length; i++) {
    		if (flights[i][0] == currNode) {
    			int adjNode = flights[i][1];
    			double currCost = costs[currNode][0] + flights[i][2];
    			// If the total cost to travel to a child node from the source,
    			// through the parent node, is less than the current total cost
    			// to get to that node, reduce the cost to the new cost.
    			if (currCost < costs[adjNode][0]) {
    				costs[adjNode][0] = currCost;
    				// If the child node's cost is less than the current minimum
    				// cost, set it as the new minimum node
    				if (costs[adjNode][0] <= minCost) {
    					minNode = adjNode;
    					minCost = costs[adjNode][0];
    					break;
    				}
    			}
    		}
    	}
		return minNode;
	}
	
	/**
     * Finds the cheapest price to travel from a source node to a destination
     * node in a weighted digraph in a given number of steps.
     * @param n The number of vertices in the digraph
     * @param flights The edges between nodes and their weights
     * @param src The source node to start at
     * @param dst The destination node to end at
     * @param K The maximum number of steps to take
     * @return The cheapest price to get to the destination node. If there is
     * no way to get to the destination node in the given number of steps,
     * returns -1.
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst,
    		int K) {
    	double[][] costs = new double[n][2];
    	int[] Q = new int[n];
    	// Set the cost of all nodes in the graph to infinity
    	for (int i = 0; i < n; i++) {
    		costs[i][0] = Double.POSITIVE_INFINITY;
    		costs[i][1] = 0;
    	}
    	// Set source as the starting node and set its cost to 0
    	costs[src][0] = 0;
    	int currNode = src;
    	int temp, count = 0;
    	// Until the destination node is reached, find the next minimum node to
    	// go to
    	while (costs[dst][1] != 1) {
    		temp = currNode;
    		// If no minimum was found by last call to extractMin, or the
    		// step limit has been exceeded, break the loop
    		if (temp == -1 || count > K) {
    			break;
    		}
    		costs[currNode][1] = 1;
    		// Call extractMin helper method to find the next cheapest node
    		currNode = extractMin(currNode, costs, flights, n);
    		costs[temp][1] = 1;
    		count++;
    	}
    	// If the destination was reached in the desired number of steps
    	// return its price, otherwise return -1
    	return (costs[dst][0] < Double.POSITIVE_INFINITY) ?
    			(int) costs[dst][0] : flights[n-1][2];
    }
}