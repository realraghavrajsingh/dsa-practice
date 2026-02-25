package com.leetcode.hard.graph;

import java.util.*;

/**
 * LC127: Word Ladder
 * Difficulty: Medium (often asked as Hard) | Google: L5
 * Type: Graph, BFS
 * Tags: graph, bfs, faang
 *
 * <h2>Problem Statement</h2>
 * <p>A transformation sequence from word {@code beginWord} to word {@code endWord} using a dictionary
 * {@code wordList} is a sequence of words beginWord → s₁ → s₂ → ... → sₖ such that: Every adjacent pair
 * of words differs by a single letter. Every sᵢ for 1 ≤ i ≤ k is in wordList. Note that beginWord does not
 * need to be in wordList. sₖ == endWord. Given two words, beginWord and endWord, and a dictionary wordList,
 * return the <b>number of words</b> in the shortest transformation sequence from beginWord to endWord, or
 * 0 if no such sequence exists.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>1 ≤ beginWord.length ≤ 10</li>
 *   <li>endWord.length == beginWord.length</li>
 *   <li>1 ≤ wordList.length ≤ 5000</li>
 *   <li>wordList[i].length == beginWord.length</li>
 *   <li>beginWord, endWord, and wordList[i] consist of lowercase English letters.</li>
 *   <li>beginWord != endWord</li>
 *   <li>All words in wordList are unique.</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  beginWord = "hit", endWord = "cog",
 *           wordList = ["hot","dot","dog","lot","log","cog"]
 *   Output: 5
 *   Explanation: hit → hot → dot → dog → cog (5 words).
 *
 * Example 2:
 *   Input:  beginWord = "hit", endWord = "cog",
 *           wordList = ["hot","dot","dog","lot","log"]
 *   Output: 0
 *   Explanation: endWord "cog" is not in wordList, no valid sequence.
 *
 * Example 3:
 *   Input:  beginWord = "a", endWord = "c", wordList = ["a","b","c"]
 *   Output: 2
 *   Explanation: a → c (2 words).
 * </pre>
 *
 * <p>Approach: BFS; try changing each char to 'a'-'z'.</p>
 * <p>Time: O(M² * N) where M=word length, N=wordList size | Space: O(N)</p>
 */
public class LC127_WordLadder_Hard_Graph {

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord)) return 0;
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        int steps = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String word = q.poll();
                if (word.equals(endWord)) return steps;
                char[] arr = word.toCharArray();
                for (int j = 0; j < arr.length; j++) {
                    char orig = arr[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == orig) continue;
                        arr[j] = c;
                        String next = new String(arr);
                        if (set.contains(next)) {
                            set.remove(next);
                            q.offer(next);
                        }
                    }
                    arr[j] = orig;
                }
            }
            steps++;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(ladderLength("hit", "cog", List.of("hot", "dot", "dog", "lot", "log", "cog"))); // 5
        System.out.println(ladderLength("hit", "cog", List.of("hot", "dot", "dog", "lot", "log")));        // 0
    }
}
