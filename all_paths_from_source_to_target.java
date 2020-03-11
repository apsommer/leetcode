/*

Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1, and return them in any order.

The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.

Example:

Input: [[1,2], [3], [3], []]
Output: [[0,1,3],[0,2,3]]

Explanation: The graph looks like this:
0--->1
|    |
v    v
2--->3

There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.

Note:

The number of nodes in the graph will be in the range [2, 15].
You can print different paths in any order, but you should keep the order of nodes inside one path.
*/

class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {

        // all paths from 0 to n-1
        List<List<Integer>> output = new ArrayList<>();
        int target = graph.length - 1;

        // build a tracking array
        List<List<Integer>> visited = new ArrayList<>();
        for (int i = 0; i < target; i++) {

            // neighbors of this node
            List<Integer> neighbors = new ArrayList<>();

            // mark the neighbor as a zero since we haven't visited it yet
            for (int j : graph[i]) {
                neighbors.add(0);
            }
            visited.add(neighbors);
        }

        // create a stack that starts with 0 as the bottom element
        Stack stack = new Stack();
        stack.push(0);

        // loop through the stack while it still has nodes to process
        while (!stack.isEmpty()) {

            // get top element = last in
            int start = (int) stack.peek();

            // loop through the neighbors of this node
            int count = 0;
            for (int i = 0; i < graph[start].length; i++) {

                // get the ending node from the input array
                int end = graph[start][i];

                // we have not yet visited this node
                if (visited.get(start).get(i) == 0) {

                    // push the node to the top of the stack
                    stack.push(end);

                    // mark this node as visited
                    visited.get(start).set(i, 1);

                    // we have reached the n-1 target
                    if (end == target) {

                        // add this path to the output array, must copy the stack as Java default is a dynamic ref
                        output.add((Stack) stack.clone());

                        // pop the target node off the stack
                        stack.pop();
                    }

                    // break out of the neighbor loop and evaluate the while condition
                    break;

                }

                // we already visited this node
                else {

                    // increment the neighbor counter
                    count ++;

                    // we have processed all adjacent nodes
                    if (count == graph[start].length) {

                        // reset the neighbor tracker back to zeros since we are about to pop and start a new path
                        for (int j = 0; j < visited.get(start).size(); j++) {
                            visited.get(start).set(j, 0);
                        }

                        // pop this node off the stack to consider previous element on next loop iteration
                        stack.pop();
                    }
                }
            }
        }
        return output;
    }
}
