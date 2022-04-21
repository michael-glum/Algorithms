/*
 * Implementation of Dijkstra's Algorithm to find the maximum amongst the
 * shortest paths from a given source node to all other nodes.
 * Copyright 2021 Michael Glum
 */

class Solution {
	/**
     * Helper method used to find the node with the minimum cost.
     * @param times The array of directed edges with weights
     * @param N The number of nodes
     * @param visitedNodes Array with visited nodes having a value of 1
     * and unvisited nodes having a value of 0
     * @return The node with the minimum cost
     */
	public static int extract_Min(int[][] times, int N, int costs[],
			int visitedNodes[]) {
		int minVal = Integer.MAX_VALUE;
		int minNode = 1;
		for (int i = 0; i < N; i++) {
			// If the cost of a node is less than the current minimum,
			// set it as the minimum node
			if (costs[i] < minVal) {
				if (visitedNodes[i] != 1) {
					minNode = i + 1;
					minVal = costs[i];
				}
			}
		}
		return minNode;
	}
	
	/**
     * Helper method used to find the maximum among the costs, to determine the
     * total time to reach all nodes.
     * @param costs Array of costs of the minimum paths to each node
     * @return The total time it takes for all nodes to receive the signal, or
     * negative one if it is impossible.
     */
	public int countTime(int[] costs) {
		int maxShortestPath = 0;
		for (int i = 0; i < costs.length; i++) {
			// If a node has not been visited, return negative one
			if (costs[i] == Integer.MAX_VALUE) {
				maxShortestPath = -1;
				break;
			}
			// If the cost of a node is greater than the current maximum cost
			// set its cost as the new maximum cost
			if (costs[i] > maxShortestPath) {
				maxShortestPath = costs[i];
			}
		}
		return maxShortestPath;
	}
	
	/**
     * Given a list of travel times as directed edges, if a signal is sent
     * from a source node, determine how long it will take for all nodes to
     * receive the signal. Calls exctract_Min and counTime as helper methods.
     * @param times The array of directed edges with weights
     * @param N The number of nodes
     * @param K The source node
     * @return The total time it takes for all nodes to receive the signal, or
     * negative one if it is impossible.
     */
    public int networkDelayTime(int[][] times, int N, int K) {
    	int[] visitedNodes = new int[N];
    	int[] costs = new int[N];
    	// Set all costs to infinity
    	for (int i = 0; i < N; i++) {
    		costs[i] = Integer.MAX_VALUE;
    	}
    	// Set the cost of the source node to 0
    	costs[K - 1] = 0;
    	// Loop through every edge in the graph
    	for (int j = 0; j < times.length; j++) {
    		// Call helper method to find the node with the minimum
    		// cost
    		int minNode = extract_Min(times, N, costs, visitedNodes);
    		// Mark the minimum node as visited
    		visitedNodes[minNode - 1] = 1;
    		// For each adjacent node to the minimum node, reduce its cost
    		// if applicable
    		for (int i = 0; i < times.length; i++) {
    			if (times[i][0] == minNode) {
    				int v = times[i][1];
    				if (visitedNodes[v - 1] != 1) {
    					if (costs[v - 1] > costs[minNode - 1] + times[i][2]) {
    						costs[v - 1] = costs[minNode - 1] + times[i][2];
    						
    					}
    				}
    			}
    		}
    	}
    	// Call helper method count time to determine the total time
    	return countTime(costs);
    }
}
