package com.leetcode.easy.twopointers;

/**
 * LC283: Move Zeroes
 * Difficulty: Easy | Google: L3
 * Type: Two Pointers, Array
 * Tags: twopointers, array, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Given an integer array {@code nums}, move all {@code 0}'s to the end of it while maintaining the
 * relative order of the non-zero elements.</p>
 * <p>Note that you must do this in-place without making a copy of the array.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>1 ≤ nums.length ≤ 10⁴</li>
 *   <li>-2³¹ ≤ nums[i] ≤ 2³¹ - 1</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  nums = [0, 1, 0, 3, 12]
 *   Output: [1, 3, 12, 0, 0]
 *   Explanation: Non-zeros [1,3,12] keep order; zeros moved to end.
 *
 * Example 2:
 *   Input:  nums = [0]
 *   Output: [0]
 * </pre>
 *
 * <p>Approach: Two pointers - slow for write position, fast for scan.</p>
 * <p>Time: O(n) | Space: O(1)</p>
 */
public class LC283_MoveZeroes_Easy_TwoPointers {

    public static void moveZeroes(int[] nums) {
        int write = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) nums[write++] = nums[i];
        }
        while (write < nums.length) nums[write++] = 0;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);
        System.out.println(java.util.Arrays.toString(nums)); // [1, 3, 12, 0, 0]
    }
}
