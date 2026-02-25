package com.leetcode.medium.dynamicprogramming;

import java.util.Arrays;

/**
 * LC300: Longest Increasing Subsequence
 * Difficulty: Medium | Google: L4
 * Type: Dynamic Programming, Binary Search
 * Tags: dp, binarysearch, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Given an integer array {@code nums}, return the length of the longest <b>strictly increasing
 * subsequence</b>. A subsequence is derived by deleting some or no elements without changing the order
 * of the remaining elements.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>1 ≤ nums.length ≤ 2500</li>
 *   <li>-10⁴ ≤ nums[i] ≤ 10⁴</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  nums = [10, 9, 2, 5, 3, 7, 101, 18]
 *   Output: 4
 *   Explanation: The longest increasing subsequence is [2, 3, 7, 101].
 *
 * Example 2:
 *   Input:  nums = [0, 1, 0, 3, 2, 3]
 *   Output: 4
 *   Explanation: [0, 1, 2, 3]
 *
 * Example 3:
 *   Input:  nums = [7, 7, 7, 7, 7, 7, 7]
 *   Output: 1
 * </pre>
 *
 * <p>Approach: DP - dp[i] = LIS ending at i. O(n²).</p>
 * <p>Time: O(n²) | Space: O(n)</p>
 */
public class LC300_LongestIncreasingSubsequence_Medium_DP {

    public static int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18})); // 4
        System.out.println(lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}));          // 4
    }
}
