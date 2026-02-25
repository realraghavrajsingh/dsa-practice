package com.leetcode.easy.array;

/**
 * LC053: Maximum Subarray (Kadane's Algorithm)
 * Difficulty: Easy | Google: L3
 * Type: Array, Dynamic Programming
 * Tags: array, dp, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Given an integer array {@code nums}, find the <b>subarray</b> with the largest sum, and return its sum.</p>
 * <p>A <b>subarray</b> is a contiguous part of an array.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>1 ≤ nums.length ≤ 10⁵</li>
 *   <li>-10⁴ ≤ nums[i] ≤ 10⁴</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
 *   Output: 6
 *   Explanation: The subarray [4, -1, 2, 1] has the largest sum 6.
 *
 * Example 2:
 *   Input:  nums = [1]
 *   Output: 1
 *   Explanation: The subarray [1] has the largest sum 1.
 *
 * Example 3:
 *   Input:  nums = [5, 4, -1, 7, 8]
 *   Output: 23
 *   Explanation: The subarray [5, 4, -1, 7, 8] has the largest sum 23.
 * </pre>
 *
 * <p>Approach: Kadane's - track current sum, reset if negative.</p>
 * <p>Time: O(n) | Space: O(1)</p>
 */
public class LC053_MaxSubarray_Easy_Array {

    public static int maxSubArray(int[] nums) {
        int maxSum = nums[0], current = nums[0];
        for (int i = 1; i < nums.length; i++) {
            current = Math.max(nums[i], current + nums[i]);
            maxSum = Math.max(maxSum, current);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4})); // 6
    }
}
