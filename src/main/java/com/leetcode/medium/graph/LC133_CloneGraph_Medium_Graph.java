package com.leetcode.medium.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LC133: Clone Graph
 * Difficulty: Medium | Google: L4
 * Type: Graph, DFS/BFS
 * Tags: graph, dfs, hashmap, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Given a reference of a node in a <b>connected undirected graph</b>, return a <b>deep copy</b>
 * (clone) of the graph. Each node in the graph contains a value ({@code int}) and a list of its
 * neighbors ({@code List<Node>}).</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>The number of nodes in the graph is in the range [0, 100].</li>
 *   <li>1 ≤ Node.val ≤ 100</li>
 *   <li>Node.val is unique for each node.</li>
 *   <li>There are no repeated edges and no self-loops.</li>
 *   <li>The graph is connected and all nodes can be visited starting from the given node.</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  adjList = [[2,4],[1,3],[2,4],[1,3]]
 *   Output: [[2,4],[1,3],[2,4],[1,3]]
 *   Explanation: Node 1 → neighbors [2,4]; Node 2 → [1,3]; etc. Deep copy preserves structure.
 *
 * Example 2:
 *   Input:  adjList = [[]]
 *   Output: [[]]
 *   Explanation: Single node with no neighbors.
 *
 * Example 3:
 *   Input:  adjList = []
 *   Output: []
 *   Explanation: Empty graph.
 * </pre>
 *
 * <p>Approach: DFS with a visited map (original → clone). For each node, create its clone,
 * then recursively clone each neighbor.</p>
 * <p>Time: O(V + E) | Space: O(V)</p>
 */
public class LC133_CloneGraph_Medium_Graph {

    public static class Node {
        public int val;
        public List<Node> neighbors;
        public Node(int val) {
            this.val = val;
            this.neighbors = new ArrayList<>();
        }
    }

    private final Map<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        if (visited.containsKey(node)) return visited.get(node);

        Node clone = new Node(node.val);
        visited.put(node, clone);
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(cloneGraph(neighbor));
        }
        return clone;
    }

    public static void main(String[] args) {
        Node n1 = new Node(1), n2 = new Node(2), n3 = new Node(3), n4 = new Node(4);
        n1.neighbors.add(n2); n1.neighbors.add(n4);
        n2.neighbors.add(n1); n2.neighbors.add(n3);
        n3.neighbors.add(n2); n3.neighbors.add(n4);
        n4.neighbors.add(n1); n4.neighbors.add(n3);

        LC133_CloneGraph_Medium_Graph solver = new LC133_CloneGraph_Medium_Graph();
        Node cloned = solver.cloneGraph(n1);
        System.out.println("Cloned node 1 val: " + cloned.val);                     // 1
        System.out.println("Cloned node 1 neighbors: "
                + cloned.neighbors.get(0).val + ", " + cloned.neighbors.get(1).val); // 2, 4
        System.out.println("Is deep copy: " + (cloned != n1));                       // true
    }
}
