package com.leetcode.medium.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LC210: Course Schedule II (Topological Sort)
 * Difficulty: Medium | Google: L4
 * Type: Graph, Topological Sort (BFS / Kahn's Algorithm)
 * Tags: graph, topologicalsort, bfs, faang
 *
 * <h2>Problem Statement</h2>
 * <p>There are a total of {@code numCourses} courses you have to take, labeled 0 to numCourses - 1.
 * You are given an array {@code prerequisites} where {@code prerequisites[i] = [a, b]} indicates that
 * you must take course {@code b} before course {@code a}. Return the ordering of courses you should
 * take to finish all courses. If it is impossible, return an empty array.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>1 ≤ numCourses ≤ 2000</li>
 *   <li>0 ≤ prerequisites.length ≤ numCourses × (numCourses - 1)</li>
 *   <li>prerequisites[i].length == 2</li>
 *   <li>0 ≤ a, b &lt; numCourses</li>
 *   <li>a ≠ b</li>
 *   <li>All pairs [a, b] are distinct.</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  numCourses = 2, prerequisites = [[1,0]]
 *   Output: [0,1]
 *
 * Example 2:
 *   Input:  numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 *   Output: [0,1,2,3] or [0,2,1,3]
 *
 * Example 3:
 *   Input:  numCourses = 1, prerequisites = []
 *   Output: [0]
 * </pre>
 *
 * <p>Approach: Kahn's algorithm — BFS with in-degree array. Enqueue all nodes with in-degree 0,
 * then process each, decrementing neighbors' in-degrees. If all nodes are processed, return the
 * order; otherwise a cycle exists.</p>
 * <p>Time: O(V + E) | Space: O(V + E)</p>
 */
public class LC210_CourseScheduleII_Medium_Graph {

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) adj.add(new ArrayList<>());

        for (int[] pre : prerequisites) {
            adj.get(pre[1]).add(pre[0]);
            inDegree[pre[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        int[] order = new int[numCourses];
        int idx = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            order[idx++] = course;
            for (int next : adj.get(course)) {
                if (--inDegree[next] == 0) queue.offer(next);
            }
        }
        return idx == numCourses ? order : new int[0];
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                findOrder(2, new int[][]{{1, 0}}))); // [0, 1]
        System.out.println(Arrays.toString(
                findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}))); // [0,1,2,3] or [0,2,1,3]
        System.out.println(Arrays.toString(
                findOrder(1, new int[][]{}))); // [0]
    }
}
