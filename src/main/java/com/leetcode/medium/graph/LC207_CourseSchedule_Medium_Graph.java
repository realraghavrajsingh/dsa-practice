package com.leetcode.medium.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * LC207: Course Schedule
 * Difficulty: Medium | Google: L4
 * Type: Graph, Topological Sort, DFS
 * Tags: graph, topologicalsort, faang
 *
 * <h2>Problem Statement</h2>
 * <p>There are a total of {@code numCourses} courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array {@code prerequisites} where prerequisites[i] = [a, b] indicates that you must
 * take course b first if you want to take course a. For example, [0, 1] means to take course 0 you have
 * to first take course 1. Return {@code true} if you can finish all courses. Otherwise, return false.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>1 ≤ numCourses ≤ 2000</li>
 *   <li>0 ≤ prerequisites.length ≤ 5000</li>
 *   <li>prerequisites[i].length == 2</li>
 *   <li>0 ≤ a, b < numCourses</li>
 *   <li>All pairs [a, b] are distinct.</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  numCourses = 2, prerequisites = [[1, 0]]
 *   Output: true
 *   Explanation: Take 0 first, then 1. No cycle.
 *
 * Example 2:
 *   Input:  numCourses = 2, prerequisites = [[1, 0], [0, 1]]
 *   Output: false
 *   Explanation: Cycle: 0 → 1 → 0. Impossible.
 *
 * Example 3:
 *   Input:  numCourses = 1, prerequisites = []
 *   Output: true
 * </pre>
 *
 * <p>Approach: DFS cycle detection with 3-state coloring.</p>
 * <p>Time: O(V+E) | Space: O(V)</p>
 */
public class LC207_CourseSchedule_Medium_Graph {

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) graph.add(new ArrayList<>());
        for (int[] p : prerequisites) graph.get(p[1]).add(p[0]);
        int[] state = new int[numCourses]; // 0=unvisited, 1=visiting, 2=done
        for (int i = 0; i < numCourses; i++)
            if (hasCycle(graph, i, state)) return false;
        return true;
    }

    private static boolean hasCycle(List<List<Integer>> graph, int v, int[] state) {
        if (state[v] == 1) return true;
        if (state[v] == 2) return false;
        state[v] = 1;
        for (int u : graph.get(v))
            if (hasCycle(graph, u, state)) return true;
        state[v] = 2;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(canFinish(2, new int[][]{{1, 0}}));           // true
        System.out.println(canFinish(2, new int[][]{{1, 0}, {0, 1}})); // false
    }
}
