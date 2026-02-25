package com.leetcode.easy.dynamicprogramming;

/**
 * LC121: Best Time to Buy and Sell Stock
 * Difficulty: Easy | Google: L3
 * Type: Dynamic Programming, Array
 * Tags: dp, array, faang
 *
 * <h2>Problem Statement</h2>
 * <p>You are given an array {@code prices} where {@code prices[i]} is the price of a given stock on the
 * i-th day. You want to maximize your profit by choosing a <b>single day</b> to buy one stock and choosing
 * a <b>different day in the future</b> to sell that stock.</p>
 * <p>Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit,
 * return 0.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>1 ≤ prices.length ≤ 10⁵</li>
 *   <li>0 ≤ prices[i] ≤ 10⁴</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  prices = [7, 1, 5, 3, 6, 4]
 *   Output: 5
 *   Explanation: Buy on day 2 (price=1) and sell on day 5 (price=6), profit = 6-1 = 5.
 *
 * Example 2:
 *   Input:  prices = [7, 6, 4, 3, 1]
 *   Output: 0
 *   Explanation: No transactions are done and the max profit = 0.
 * </pre>
 *
 * <p>Approach: Track min price seen, max profit = price - min.</p>
 * <p>Time: O(n) | Space: O(1)</p>
 */
public class LC121_BestTimeToBuyStock_Easy_DP {

    public static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE, maxProfit = 0;
        for (int p : prices) {
            minPrice = Math.min(minPrice, p);
            maxProfit = Math.max(maxProfit, p - minPrice);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4})); // 5
        System.out.println(maxProfit(new int[]{7, 6, 4, 3, 1}));   // 0
    }
}
