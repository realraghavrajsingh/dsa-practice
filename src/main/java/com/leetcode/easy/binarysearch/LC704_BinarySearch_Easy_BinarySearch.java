package com.leetcode.easy.binarysearch;

/**
 * LC704: Binary Search
 * Difficulty: Easy | Google: L3
 * Type: Binary Search
 * Tags: binarysearch, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Given an array of integers {@code nums} which is sorted in <b>ascending order</b>, and an integer
 * {@code target}, write a function to search {@code target} in {@code nums}. If {@code target} exists,
 * then return its index. Otherwise, return -1.</p>
 * <p>You must write an algorithm with O(log n) runtime complexity.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>1 ≤ nums.length ≤ 10⁴</li>
 *   <li>-10⁴ < nums[i], target < 10⁴</li>
 *   <li>All integers in nums are unique.</li>
 *   <li>nums is sorted in ascending order.</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  nums = [-1, 0, 3, 5, 9, 12], target = 9
 *   Output: 4
 *   Explanation: 9 exists in nums and its index is 4.
 *
 * Example 2:
 *   Input:  nums = [-1, 0, 3, 5, 9, 12], target = 2
 *   Output: -1
 *   Explanation: 2 does not exist in nums so return -1.
 * </pre>
 *
 * <p>Approach: Classic binary search.</p>
 * <p>Time: O(log n) | Space: O(1)</p>
 */
public class LC704_BinarySearch_Easy_BinarySearch {

    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        System.out.println(search(nums, 9));  // 4
        System.out.println(search(nums, 2)); // -1
    }
}
