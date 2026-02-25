package com.leetcode.hard.array;

/**
 * LC041: First Missing Positive
 * Difficulty: Hard | Google: L5
 * Type: Array, Cyclic Sort
 * Tags: array, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Given an unsorted integer array {@code nums}, return the smallest positive integer that is not present
 * in {@code nums}. You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>1 ≤ nums.length ≤ 10⁵</li>
 *   <li>-2³¹ ≤ nums[i] ≤ 2³¹ - 1</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  nums = [1, 2, 0]
 *   Output: 3
 *   Explanation: 1 and 2 are present. Smallest missing positive = 3.
 *
 * Example 2:
 *   Input:  nums = [3, 4, -1, 1]
 *   Output: 2
 *   Explanation: 1, 3, 4 present. Missing = 2.
 *
 * Example 3:
 *   Input:  nums = [7, 8, 9, 11, 12]
 *   Output: 1
 *   Explanation: All positives > 1. Smallest missing = 1.
 * </pre>
 *
 * <p>Approach: Place each positive in its correct index (1 at 0, 2 at 1, ...).</p>
 * <p>Time: O(n) | Space: O(1)</p>
 */
public class LC041_FirstMissingPositive_Hard_Array {

    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] >= 1 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int t = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = t;
            }
        }
        for (int i = 0; i < n; i++)
            if (nums[i] != i + 1) return i + 1;
        return n + 1;
    }

    public static void main(String[] args) {
        System.out.println(firstMissingPositive(new int[]{1, 2, 0}));         // 3
        System.out.println(firstMissingPositive(new int[]{3, 4, -1, 1}));   // 2
        System.out.println(firstMissingPositive(new int[]{7, 8, 9, 11, 12})); // 1
    }
}
