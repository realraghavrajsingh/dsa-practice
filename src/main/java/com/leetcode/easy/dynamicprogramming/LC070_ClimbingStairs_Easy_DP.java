package com.leetcode.easy.dynamicprogramming;

/**
 * LC070: Climbing Stairs
 * Difficulty: Easy | Google: L3
 * Type: Dynamic Programming
 * Tags: dp, faang
 *
 * <h2>Problem Statement</h2>
 * <p>You are climbing a staircase. It takes {@code n} steps to reach the top.</p>
 * <p>Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>1 ≤ n ≤ 45</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  n = 2
 *   Output: 2
 *   Explanation: There are two ways to climb to the top.
 *     - 1 step + 1 step
 *     - 2 steps
 *
 * Example 2:
 *   Input:  n = 3
 *   Output: 3
 *   Explanation: There are three ways to climb to the top.
 *     - 1 step + 1 step + 1 step
 *     - 1 step + 2 steps
 *     - 2 steps + 1 step
 * </pre>
 *
 * <p>Approach: Fibonacci-like DP. dp[i] = dp[i-1] + dp[i-2].</p>
 * <p>Time: O(n) | Space: O(1)</p>
 */
public class LC070_ClimbingStairs_Easy_DP {

    public static int climbStairs(int n) {
        if (n <= 2) return n;
        int prev = 2, prevPrev = 1;
        for (int i = 3; i <= n; i++) {
            int curr = prev + prevPrev;
            prevPrev = prev;
            prev = curr;
        }
        return prev;
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(2)); // 2
        System.out.println(climbStairs(3)); // 3
    }
}
