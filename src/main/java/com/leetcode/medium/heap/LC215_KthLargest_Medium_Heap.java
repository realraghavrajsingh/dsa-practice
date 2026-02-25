package com.leetcode.medium.heap;

import java.util.PriorityQueue;

/**
 * LC215: Kth Largest Element in an Array
 * Difficulty: Medium | Google: L4
 * Type: Heap, QuickSelect
 * Tags: heap, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Given an integer array {@code nums} and an integer {@code k}, return the k-th largest element
 * in the array. Note that it is the k-th largest element in the sorted order, not the k-th distinct
 * element. Can you solve it without sorting?</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>1 ≤ k ≤ nums.length ≤ 10⁵</li>
 *   <li>-10⁴ ≤ nums[i] ≤ 10⁴</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  nums = [3, 2, 1, 5, 6, 4], k = 2
 *   Output: 5
 *   Explanation: Sorted: [6,5,4,3,2,1]. 2nd largest = 5.
 *
 * Example 2:
 *   Input:  nums = [3, 2, 3, 1, 2, 4, 5, 5, 6], k = 4
 *   Output: 4
 *   Explanation: Sorted: [6,5,5,4,3,3,2,2,1]. 4th largest = 4.
 * </pre>
 *
 * <p>Approach: Min-heap of size k. Poll when size > k.</p>
 * <p>Time: O(n log k) | Space: O(k)</p>
 */
public class LC215_KthLargest_Medium_Heap {

    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int n : nums) {
            minHeap.offer(n);
            if (minHeap.size() > k) minHeap.poll();
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        System.out.println(findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2)); // 5
        System.out.println(findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4)); // 4
    }
}
