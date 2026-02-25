package com.leetcode.hard.binarysearch;

/**
 * LC004: Median of Two Sorted Arrays
 * Difficulty: Hard | Google: L5
 * Type: Binary Search
 * Tags: binarysearch, array, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Given two sorted arrays {@code nums1} and {@code nums2} of size m and n respectively, return the
 * <b>median</b> of the two sorted arrays. The overall run time complexity should be O(log(m+n)).</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>nums1.length == m, nums2.length == n</li>
 *   <li>0 ≤ m ≤ 1000, 0 ≤ n ≤ 1000</li>
 *   <li>1 ≤ m + n ≤ 2000</li>
 *   <li>-10⁶ ≤ nums1[i], nums2[i] ≤ 10⁶</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  nums1 = [1, 3], nums2 = [2]
 *   Output: 2.0
 *   Explanation: merged = [1,2,3], median = 2.0
 *
 * Example 2:
 *   Input:  nums1 = [1, 2], nums2 = [3, 4]
 *   Output: 2.5
 *   Explanation: merged = [1,2,3,4], median = (2+3)/2 = 2.5
 * </pre>
 *
 * <p>Approach: Binary search on the shorter array to find the correct partition so that
 * all elements on the left half ≤ all elements on the right half.</p>
 * <p>Time: O(log(min(m,n))) | Space: O(1)</p>
 */
public class LC004_MedianOfTwoSortedArrays_Hard_BinarySearch {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length, n = nums2.length;
        int lo = 0, hi = m;

        while (lo <= hi) {
            int i = (lo + hi) / 2;
            int j = (m + n + 1) / 2 - i;

            int maxLeft1  = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int minRight1 = (i == m) ? Integer.MAX_VALUE : nums1[i];
            int maxLeft2  = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int minRight2 = (j == n) ? Integer.MAX_VALUE : nums2[j];

            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                if ((m + n) % 2 == 0) {
                    return (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2.0;
                }
                return Math.max(maxLeft1, maxLeft2);
            } else if (maxLeft1 > minRight2) {
                hi = i - 1;
            } else {
                lo = i + 1;
            }
        }
        throw new IllegalArgumentException("Input arrays are not sorted.");
    }

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));    // 2.0
        System.out.println(findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4})); // 2.5
    }
}
