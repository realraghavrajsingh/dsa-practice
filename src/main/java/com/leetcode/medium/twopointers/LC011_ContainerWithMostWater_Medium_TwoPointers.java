package com.leetcode.medium.twopointers;

/**
 * LC011: Container With Most Water
 * Difficulty: Medium | Google: L4
 * Type: Two Pointers, Array
 * Tags: twopointers, array, faang
 *
 * <h2>Problem Statement</h2>
 * <p>You are given an integer array {@code height} of length n. There are n vertical lines drawn such that
 * the two endpoints of the i-th line are (i, 0) and (i, height[i]). Find two lines that together with
 * the x-axis form a container that holds the most water. Return the maximum amount of water a container
 * can store. Notice that you may not slant the container.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>n == height.length</li>
 *   <li>2 ≤ n ≤ 10⁵</li>
 *   <li>0 ≤ height[i] ≤ 10⁴</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  height = [1, 8, 6, 2, 5, 4, 8, 3, 7]
 *   Output: 49
 *   Explanation: Lines at index 1 and 8: height=8, width=7, area=49.
 *
 * Example 2:
 *   Input:  height = [1, 1]
 *   Output: 1
 * </pre>
 *
 * <p>Approach: Two pointers at ends; move the shorter inward.</p>
 * <p>Time: O(n) | Space: O(1)</p>
 */
public class LC011_ContainerWithMostWater_Medium_TwoPointers {

    public static int maxArea(int[] height) {
        int left = 0, right = height.length - 1, max = 0;
        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            max = Math.max(max, area);
            if (height[left] < height[right]) left++;
            else right--;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7})); // 49
    }
}
