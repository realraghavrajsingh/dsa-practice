package com.leetcode.easy.bitmanipulation;

/**
 * LC136: Single Number
 * Difficulty: Easy | Google: L3
 * Type: Bit Manipulation
 * Tags: bitmanipulation, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Given a non-empty array of integers {@code nums}, every element appears <b>twice</b> except for one.
 * Find that single one.</p>
 * <p>You must implement a solution with a linear runtime complexity and use only constant extra space.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>1 ≤ nums.length ≤ 3 × 10⁴</li>
 *   <li>-3 × 10⁴ ≤ nums[i] ≤ 3 × 10⁴</li>
 *   <li>Each element in the array appears twice except for one element which appears only once.</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  nums = [2, 2, 1]
 *   Output: 1
 *   Explanation: Only 1 appears once.
 *
 * Example 2:
 *   Input:  nums = [4, 1, 2, 1, 2]
 *   Output: 4
 *   Explanation: Only 4 appears once.
 *
 * Example 3:
 *   Input:  nums = [1]
 *   Output: 1
 * </pre>
 *
 * <p>Approach: XOR all numbers. a ^ a = 0, so pairs cancel; remaining is the answer.</p>
 * <p>Time: O(n) | Space: O(1)</p>
 */
public class LC136_SingleNumber_Easy_BitManipulation {

    public static int singleNumber(int[] nums) {
        int result = 0;
        for (int n : nums) result ^= n;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{2, 2, 1}));           // 1
        System.out.println(singleNumber(new int[]{4, 1, 2, 1, 2}));   // 4
    }
}
