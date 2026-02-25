package com.leetcode.hard.dynamicprogramming;

/**
 * LC010: Regular Expression Matching
 * Difficulty: Hard | Google: L5
 * Type: Dynamic Programming, String
 * Tags: dp, string, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Given an input string {@code s} and a pattern {@code p}, implement regular expression matching with
 * support for '.' and '*' where: '.' Matches any single character. '*' Matches zero or more of the preceding
 * element. The matching should cover the <b>entire</b> input string (not partial).</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>1 ≤ s.length ≤ 20</li>
 *   <li>1 ≤ p.length ≤ 20</li>
 *   <li>s contains only lowercase English letters.</li>
 *   <li>p contains only lowercase English letters, '.', and '*'.</li>
 *   <li>It is guaranteed for each appearance of '*', there will be a previous valid character to match.</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  s = "aa", p = "a"
 *   Output: false
 *   Explanation: "a" does not match entire string "aa".
 *
 * Example 2:
 *   Input:  s = "aa", p = "a*"
 *   Output: true
 *   Explanation: '*' means zero or more of preceding element 'a'. "aa" matches.
 *
 * Example 3:
 *   Input:  s = "ab", p = ".*"
 *   Output: true
 *   Explanation: ".*" means "zero or more (*) of any character (.)".
 * </pre>
 *
 * <p>Approach: DP - dp[i][j] = match s[0..i) with p[0..j).</p>
 * <p>Time: O(m*n) | Space: O(m*n)</p>
 */
public class LC010_RegularExpressionMatching_Hard_DP {

    public static boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 2; j <= n; j += 2)
            if (p.charAt(j - 1) == '*') dp[0][j] = dp[0][j - 2];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2] || (match(s, p, i, j - 1) && dp[i - 1][j]);
                } else {
                    dp[i][j] = match(s, p, i, j) && dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }

    private static boolean match(String s, String p, int i, int j) {
        if (i == 0) return false;
        return p.charAt(j - 1) == '.' || s.charAt(i - 1) == p.charAt(j - 1);
    }

    public static void main(String[] args) {
        System.out.println(isMatch("aa", "a"));   // false
        System.out.println(isMatch("aa", "a*"));  // true
        System.out.println(isMatch("ab", ".*")); // true
    }
}
