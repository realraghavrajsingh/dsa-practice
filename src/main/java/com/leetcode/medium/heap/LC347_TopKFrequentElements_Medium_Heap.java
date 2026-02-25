package com.leetcode.medium.heap;

import java.util.*;

/**
 * LC347: Top K Frequent Elements
 * Difficulty: Medium | Google: L4
 * Type: Heap, Hash Map
 * Tags: heap, hashmap, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Given an integer array {@code nums} and an integer {@code k}, return the {@code k} most frequent
 * elements. You may return the answer in any order. It is guaranteed that the answer is unique.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>1 ≤ nums.length ≤ 10⁵</li>
 *   <li>-10⁴ ≤ nums[i] ≤ 10⁴</li>
 *   <li>k is in the range [1, number of unique elements].</li>
 *   <li>The answer is unique.</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  nums = [1, 1, 1, 2, 2, 3], k = 2
 *   Output: [1, 2]
 *   Explanation: 1 appears 3 times, 2 appears 2 times. Top 2 frequent.
 *
 * Example 2:
 *   Input:  nums = [1], k = 1
 *   Output: [1]
 * </pre>
 *
 * <p>Approach: Count frequencies, min-heap of size k.</p>
 * <p>Time: O(n log k) | Space: O(n)</p>
 */
public class LC347_TopKFrequentElements_Medium_Heap {

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int n : nums) count.merge(n, 1, Integer::sum);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        for (Map.Entry<Integer, Integer> e : count.entrySet()) {
            pq.offer(new int[]{e.getKey(), e.getValue()});
            if (pq.size() > k) pq.poll();
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) result[i] = pq.poll()[0];
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));
        // [1, 2] or [2, 1]
    }
}
