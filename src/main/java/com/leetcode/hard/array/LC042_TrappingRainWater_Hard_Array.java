package com.leetcode.hard.array;

/**
 * LC042: Trapping Rain Water
 * Difficulty: Hard | Google: L5
 * Type: Array, Two Pointers
 * Tags: array, twopointers, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Given {@code n} non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it can trap after raining. Each bar has width 1 and height given by the array.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>n == height.length</li>
 *   <li>1 ≤ n ≤ 2 × 10⁵</li>
 *   <li>0 ≤ height[i] ≤ 10⁵</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  height = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]
 *   Output: 6
 *   Explanation: The elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 *   In this case, 6 units of rain water are being trapped.
 *
 * Example 2:
 *   Input:  height = [4, 2, 0, 3, 2, 5]
 *   Output: 9
 * </pre>
 *
 * <p>Approach: Two pointers; track maxLeft and maxRight.</p>
 * <p>Time: O(n) | Space: O(1)</p>
 */
public class LC042_TrappingRainWater_Hard_Array {

    public static int trap(int[] height) {
        if (height == null || height.length < 3) return 0;
        int left = 0, right = height.length - 1;
        int maxLeft = 0, maxRight = 0, water = 0;
        while (left < right) {
            if (height[left] <= height[right]) {
                if (height[left] >= maxLeft) maxLeft = height[left];
                else water += maxLeft - height[left];
                left++;
            } else {
                if (height[right] >= maxRight) maxRight = height[right];
                else water += maxRight - height[right];
                right--;
            }
        }
        return water;
    }

    public static void main(String[] args) {
        System.out.println(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1})); // 6
    }
}
