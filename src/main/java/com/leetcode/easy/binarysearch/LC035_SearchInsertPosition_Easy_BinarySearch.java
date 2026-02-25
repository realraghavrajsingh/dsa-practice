package com.leetcode.easy.binarysearch;

/**
 * LC035: Search Insert Position
 * Difficulty: Easy | Google: L3
 * Type: Binary Search
 * Tags: binarysearch, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Given a sorted array of distinct integers and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.</p>
 * <p>You must write an algorithm with O(log n) runtime complexity.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>1 ≤ nums.length ≤ 10⁴</li>
 *   <li>-10⁴ ≤ nums[i], target ≤ 10⁴</li>
 *   <li>nums contains distinct values sorted in ascending order.</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  nums = [1, 3, 5, 6], target = 5
 *   Output: 2
 *   Explanation: 5 is found at index 2.
 *
 * Example 2:
 *   Input:  nums = [1, 3, 5, 6], target = 2
 *   Output: 1
 *   Explanation: 2 is not found. It would be inserted at index 1 (between 1 and 3).
 *
 * Example 3:
 *   Input:  nums = [1, 3, 5, 6], target = 7
 *   Output: 4
 *   Explanation: 7 is not found. It would be inserted at index 4 (at the end).
 * </pre>
 *
 * <p>Approach: Binary search; return left when not found.</p>
 * <p>Time: O(log n) | Space: O(1)</p>
 */
public class LC035_SearchInsertPosition_Easy_BinarySearch {

    public static int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return left;
    }

    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 5)); // 2
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 2)); // 1
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 7)); // 4
    }
}
