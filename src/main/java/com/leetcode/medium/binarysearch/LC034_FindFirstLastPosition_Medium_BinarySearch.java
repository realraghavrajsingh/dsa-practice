package com.leetcode.medium.binarysearch;

/**
 * LC034: Find First and Last Position of Element in Sorted Array
 * Difficulty: Medium | Google: L4
 * Type: Binary Search
 * Tags: binarysearch, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Given an array of integers {@code nums} sorted in non-decreasing order, find the starting and ending
 * position of a given {@code target} value. If {@code target} is not found in the array, return [-1, -1].</p>
 * <p>You must write an algorithm with O(log n) runtime complexity.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>0 ≤ nums.length ≤ 10⁵</li>
 *   <li>-10⁹ ≤ nums[i] ≤ 10⁹</li>
 *   <li>nums is a non-decreasing array.</li>
 *   <li>-10⁹ ≤ target ≤ 10⁹</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  nums = [5, 7, 7, 8, 8, 10], target = 8
 *   Output: [3, 4]
 *   Explanation: 8 appears at indices 3 and 4.
 *
 * Example 2:
 *   Input:  nums = [5, 7, 7, 8, 8, 10], target = 6
 *   Output: [-1, -1]
 *
 * Example 3:
 *   Input:  nums = [], target = 0
 *   Output: [-1, -1]
 * </pre>
 *
 * <p>Approach: Two binary searches - one for left bound, one for right.</p>
 * <p>Time: O(log n) | Space: O(1)</p>
 */
public class LC034_FindFirstLastPosition_Medium_BinarySearch {

    public static int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        if (nums.length == 0) return result;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) left = mid + 1;
            else right = mid;
        }
        if (nums[left] != target) return result;
        result[0] = left;
        right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2 + 1;
            if (nums[mid] > target) right = mid - 1;
            else left = mid;
        }
        result[1] = right;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(java.util.Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));
        // [3, 4]
        System.out.println(java.util.Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6)));
        // [-1, -1]
    }
}
