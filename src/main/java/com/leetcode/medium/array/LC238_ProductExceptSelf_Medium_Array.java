package com.leetcode.medium.array;

/**
 * LC238: Product of Array Except Self
 * Difficulty: Medium | Google: L4
 * Type: Array
 * Tags: array, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Given an integer array {@code nums}, return an array {@code answer} such that {@code answer[i]} is
 * equal to the product of all the elements of {@code nums} except {@code nums[i]}.</p>
 * <p>You must write an algorithm that runs in O(n) time and without using the division operation.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>2 ≤ nums.length ≤ 10⁵</li>
 *   <li>-30 ≤ nums[i] ≤ 30</li>
 *   <li>The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  nums = [1, 2, 3, 4]
 *   Output: [24, 12, 8, 6]
 *   Explanation: answer[0] = 2*3*4 = 24, answer[1] = 1*3*4 = 12, etc.
 *
 * Example 2:
 *   Input:  nums = [-1, 1, 0, -3, 3]
 *   Output: [0, 0, 9, 0, 0]
 * </pre>
 *
 * <p>Approach: Two passes - left products, then right products.</p>
 * <p>Time: O(n) | Space: O(1) excluding output</p>
 */
public class LC238_ProductExceptSelf_Medium_Array {

    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        result[0] = 1;
        for (int i = 1; i < n; i++) result[i] = result[i - 1] * nums[i - 1];
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= right;
            right *= nums[i];
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(java.util.Arrays.toString(productExceptSelf(new int[]{1, 2, 3, 4})));
        // [24, 12, 8, 6]
    }
}
