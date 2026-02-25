package com.leetcode.medium.binarysearch;

/**
 * LC033: Search in Rotated Sorted Array
 * Difficulty: Medium | Google: L4
 * Type: Binary Search
 * Tags: binarysearch, faang
 *
 * <h2>Problem Statement</h2>
 * <p>There is an integer array {@code nums} sorted in ascending order (with distinct values). Prior to
 * being passed to your function, {@code nums} is possibly rotated at an unknown pivot index {@code k}
 * (1 ≤ k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0],
 * nums[1], ..., nums[k-1]].</p>
 * <p>Given the array {@code nums} after the possible rotation and an integer {@code target}, return the
 * index of {@code target} if it is in {@code nums}, or -1 if it is not. You must write an algorithm with
 * O(log n) runtime complexity.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>1 ≤ nums.length ≤ 5000</li>
 *   <li>-10⁴ ≤ nums[i] ≤ 10⁴</li>
 *   <li>All values of nums are unique.</li>
 *   <li>nums is an ascending array that is possibly rotated.</li>
 *   <li>-10⁴ ≤ target ≤ 10⁴</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  nums = [4, 5, 6, 7, 0, 1, 2], target = 0
 *   Output: 4
 *   Explanation: 0 is at index 4.
 *
 * Example 2:
 *   Input:  nums = [4, 5, 6, 7, 0, 1, 2], target = 3
 *   Output: -1
 *
 * Example 3:
 *   Input:  nums = [1], target = 0
 *   Output: -1
 * </pre>
 *
 * <p>Approach: Binary search; one half is always sorted.</p>
 * <p>Time: O(log n) | Space: O(1)</p>
 */
public class LC033_SearchRotatedArray_Medium_BinarySearch {

    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[left] <= nums[mid]) {
                if (target >= nums[left] && target < nums[mid]) right = mid - 1;
                else left = mid + 1;
            } else {
                if (target > nums[mid] && target <= nums[right]) left = mid + 1;
                else right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0)); // 4
        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3)); // -1
    }
}
