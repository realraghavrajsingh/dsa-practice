package com.leetcode.hard.graph;

import java.util.*;

/**
 * LC269: Alien Dictionary (Premium)
 * Difficulty: Hard | Google: L5
 * Type: Graph, Topological Sort
 * Tags: graph, topologicalsort, faang
 *
 * <h2>Problem Statement</h2>
 * <p>There is a new alien language that uses the English alphabet. However, the order among the letters
 * is unknown to you. You are given a list of strings {@code words} from the alien language's dictionary,
 * where the strings are <b>sorted lexicographically</b> by the rules of this new language. Return a string
 * of the unique letters in the new alien language sorted in lexicographically increasing order by the new
 * language's rules. If there is no solution, return "". If there are multiple solutions, return any of them.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>1 ≤ words.length ≤ 100</li>
 *   <li>1 ≤ words[i].length ≤ 100</li>
 *   <li>words[i] consists of only lowercase English letters.</li>
 *   <li>If "a" comes before "b" in words, then in the alien language "a" comes before "b".</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  words = ["wrt","wrf","er","ett","rftt"]
 *   Output: "wertf"
 *   Explanation: Order derived from adjacent words: wrt before wrf (t before f), er before ett (r before t),
 *                ett before rftt (e before r). Result: w, e, r, t, f.
 *
 * Example 2:
 *   Input:  words = ["z","x"]
 *   Output: "zx"
 *
 * Example 3:
 *   Input:  words = ["z","x","z"]
 *   Output: ""
 *   Explanation: Invalid - "z" before "z" implies contradiction.
 * </pre>
 *
 * <p>Approach: Build graph from adjacent word pairs, topological sort.</p>
 * <p>Time: O(C) total chars | Space: O(1)</p>
 */
public class LC269_AlienDictionary_Hard_Graph {

    public static String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();
        for (String w : words)
            for (char c : w.toCharArray())
                inDegree.putIfAbsent(c, 0);
        for (int i = 0; i < words.length - 1; i++) {
            String a = words[i], b = words[i + 1];
            if (a.length() > b.length() && a.startsWith(b)) return "";
            for (int j = 0; j < Math.min(a.length(), b.length()); j++) {
                char c1 = a.charAt(j), c2 = b.charAt(j);
                if (c1 != c2) {
                    if (graph.computeIfAbsent(c1, k -> new HashSet<>()).add(c2))
                        inDegree.merge(c2, 1, Integer::sum);
                    break;
                }
            }
        }
        Queue<Character> q = new LinkedList<>();
        for (Map.Entry<Character, Integer> e : inDegree.entrySet())
            if (e.getValue() == 0) q.offer(e.getKey());
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            char c = q.poll();
            sb.append(c);
            for (char next : graph.getOrDefault(c, Collections.emptySet())) {
                inDegree.merge(next, -1, Integer::sum);
                if (inDegree.get(next) == 0) q.offer(next);
            }
        }
        return sb.length() == inDegree.size() ? sb.toString() : "";
    }

    public static void main(String[] args) {
        System.out.println(alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt"})); // wertf
    }
}
