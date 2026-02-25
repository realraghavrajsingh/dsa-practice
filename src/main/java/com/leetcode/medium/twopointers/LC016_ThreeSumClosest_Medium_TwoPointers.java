package com.leetcode.medium.twopointers;

import java.util.Arrays;

/**
 * LC016: 3Sum Closest
 * Difficulty: Medium | Google: L4
 * Type: Two Pointers, Array
 * Tags: twopointers, array, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Given an integer array {@code nums} of length n and an integer {@code target}, find three integers
 * in {@code nums} such that the sum is closest to {@code target}. Return the sum of the three integers.
 * You may assume that each input would have exactly one solution.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>3 ≤ nums.length ≤ 500</li>
 *   <li>-1000 ≤ nums[i] ≤ 1000</li>
 *   <li>-10⁴ ≤ target ≤ 10⁴</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  nums = [-1, 2, 1, -4], target = 1
 *   Output: 2
 *   Explanation: The sum that is closest to target is 2. (-1 + 2 + 1 = 2).
 *
 * Example 2:
 *   Input:  nums = [0, 0, 0], target = 1
 *   Output: 0
 * </pre>
 *
 * <p>Approach: Sort + two pointers.</p>
 * <p>Time: O(n²) | Space: O(1)</p>
 */
public class LC016_ThreeSumClosest_Medium_TwoPointers {

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(sum - target) < Math.abs(closest - target)) closest = sum;
                if (sum < target) left++;
                else if (sum > target) right--;
                else return target;
            }
        }
        return closest;
    }

    public static void main(String[] args) {
        System.out.println(threeSumClosest(new int[]{-1, 2, 1, -4}, 1)); // 2
    }
}
