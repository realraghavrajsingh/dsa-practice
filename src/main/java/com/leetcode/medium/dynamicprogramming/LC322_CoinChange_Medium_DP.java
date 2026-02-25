package com.leetcode.medium.dynamicprogramming;

import java.util.Arrays;

/**
 * LC322: Coin Change
 * Difficulty: Medium | Google: L4
 * Type: Dynamic Programming
 * Tags: dp, faang
 *
 * <h2>Problem Statement</h2>
 * <p>You are given an integer array {@code coins} representing coins of different denominations and an
 * integer {@code amount} representing a total amount of money. Return the <b>fewest number of coins</b>
 * that you need to make up that amount. If that amount of money cannot be made up by any combination of
 * the coins, return -1. You may assume that you have an infinite number of each kind of coin.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>1 ≤ coins.length ≤ 12</li>
 *   <li>1 ≤ coins[i] ≤ 2³¹ - 1</li>
 *   <li>0 ≤ amount ≤ 10⁴</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  coins = [1, 2, 5], amount = 11
 *   Output: 3
 *   Explanation: 11 = 5 + 5 + 1
 *
 * Example 2:
 *   Input:  coins = [2], amount = 3
 *   Output: -1
 *   Explanation: Cannot make 3 with only 2's.
 *
 * Example 3:
 *   Input:  coins = [1], amount = 0
 *   Output: 0
 * </pre>
 *
 * <p>Approach: dp[i] = min coins for amount i. dp[i] = 1 + min(dp[i-coin]).</p>
 * <p>Time: O(amount * coins) | Space: O(amount)</p>
 */
public class LC322_CoinChange_Medium_DP {

    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int c : coins) {
                if (i >= c) dp[i] = Math.min(dp[i], 1 + dp[i - c]);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1, 2, 5}, 11));   // 3
        System.out.println(coinChange(new int[]{2}, 3));           // -1
    }
}
