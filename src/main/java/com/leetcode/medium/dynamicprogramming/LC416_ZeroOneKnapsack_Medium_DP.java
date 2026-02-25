package com.leetcode.medium.dynamicprogramming;

/**
 * LC416: 0/1 Knapsack
 * Difficulty: Medium | Google: L4
 * Type: Dynamic Programming
 * Tags: dp, knapsack, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Given {@code n} items with weights {@code weights[]} and values {@code values[]}, and a knapsack of
 * capacity {@code W}, find the <b>maximum value</b> you can obtain by selecting items such that the total
 * weight does not exceed the capacity. Each item can be used <b>at most once</b> (0/1 choice).</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>1 ≤ n ≤ 100</li>
 *   <li>1 ≤ weights[i], values[i] ≤ 1000</li>
 *   <li>1 ≤ W ≤ 10⁴</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  weights = [1, 2, 3], values = [6, 10, 12], capacity = 5
 *   Output: 22
 *   Explanation: Take items 1 and 2 (weight 2+3=5, value 10+12=22).
 *
 * Example 2:
 *   Input:  weights = [10, 20, 30], values = [60, 100, 120], capacity = 50
 *   Output: 220
 *   Explanation: Take items 1 and 2 (weight 20+30=50, value 100+120=220).
 * </pre>
 *
 * <p>Approach: dp[i][w] = max value using first i items with capacity w. Either skip item i or take it.</p>
 * <p>Time: O(n * W) | Space: O(W) with 1D optimization</p>
 */
public class LC416_ZeroOneKnapsack_Medium_DP {

    /**
     * 0/1 Knapsack - 1D DP (space optimized).
     * dp[w] = max value achievable with capacity w.
     * Iterate items, iterate capacity backwards to avoid reusing same item.
     */
    public static int knapsack(int[] weights, int[] values, int capacity) {
        int[] dp = new int[capacity + 1];
        for (int i = 0; i < weights.length; i++) {
            for (int w = capacity; w >= weights[i]; w--) {
                dp[w] = Math.max(dp[w], values[i] + dp[w - weights[i]]);
            }
        }
        return dp[capacity];
    }

    /**
     * 2D DP version (easier to understand).
     * dp[i][w] = max value using items 0..i-1 with capacity w.
     */
    public static int knapsack2D(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                dp[i][w] = dp[i - 1][w]; // skip item i-1
                if (w >= weights[i - 1]) {
                    dp[i][w] = Math.max(dp[i][w], values[i - 1] + dp[i - 1][w - weights[i - 1]]);
                }
            }
        }
        return dp[n][capacity];
    }

    public static void main(String[] args) {
        System.out.println(knapsack(new int[]{1, 2, 3}, new int[]{6, 10, 12}, 5));       // 22
        System.out.println(knapsack(new int[]{10, 20, 30}, new int[]{60, 100, 120}, 50)); // 220
    }
}
