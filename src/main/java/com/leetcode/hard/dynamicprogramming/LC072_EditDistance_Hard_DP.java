package com.leetcode.hard.dynamicprogramming;

/**
 * LC072: Edit Distance (Levenshtein)
 * Difficulty: Hard | Google: L5
 * Type: Dynamic Programming
 * Tags: dp, string, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Given two strings {@code word1} and {@code word2}, return the minimum number of operations required
 * to convert {@code word1} to {@code word2}. You have the following three operations permitted on a word:
 * Insert a character, Delete a character, Replace a character.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>0 ≤ word1.length, word2.length ≤ 500</li>
 *   <li>word1 and word2 consist of lowercase English letters.</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  word1 = "horse", word2 = "ros"
 *   Output: 3
 *   Explanation: horse → rorse (replace h→r), rorse → rose (remove r), rose → ros (remove e).
 *
 * Example 2:
 *   Input:  word1 = "intention", word2 = "execution"
 *   Output: 5
 *   Explanation: intention → inention (remove t), inention → enention (replace i→e),
 *                enention → exention (replace n→x), exention → exection (replace n→c),
 *                exection → execution (insert u).
 * </pre>
 *
 * <p>Approach: dp[i][j] = min edits for word1[0..i) to word2[0..j).</p>
 * <p>Time: O(m*n) | Space: O(m*n)</p>
 */
public class LC072_EditDistance_Hard_DP {

    public static int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) dp[i][0] = i;
        for (int j = 0; j <= n; j++) dp[0][j] = j;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println(minDistance("horse", "ros"));   // 3
        System.out.println(minDistance("intention", "execution")); // 5
    }
}
