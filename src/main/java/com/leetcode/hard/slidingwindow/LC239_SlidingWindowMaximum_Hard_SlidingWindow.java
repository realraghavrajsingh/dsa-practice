package com.leetcode.hard.slidingwindow;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * LC239: Sliding Window Maximum
 * Difficulty: Hard | Google: L5
 * Type: Sliding Window, Monotonic Deque
 * Tags: slidingwindow, deque, faang
 *
 * <h2>Problem Statement</h2>
 * <p>You are given an array of integers {@code nums}, there is a sliding window of size {@code k} which
 * moves from the very left to the very right. You can only see the {@code k} numbers in the window.
 * Each time the window moves right by one position. Return the max sliding window.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>1 ≤ nums.length ≤ 10⁵</li>
 *   <li>-10⁴ ≤ nums[i] ≤ 10⁴</li>
 *   <li>1 ≤ k ≤ nums.length</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  nums = [1,3,-1,-3,5,3,6,7], k = 3
 *   Output: [3,3,5,5,6,7]
 *   Explanation:
 *     Window [1,3,-1]    → max = 3
 *     Window [3,-1,-3]   → max = 3
 *     Window [-1,-3,5]   → max = 5
 *     Window [-3,5,3]    → max = 5
 *     Window [5,3,6]     → max = 6
 *     Window [3,6,7]     → max = 7
 *
 * Example 2:
 *   Input:  nums = [1], k = 1
 *   Output: [1]
 * </pre>
 *
 * <p>Approach: Monotonic decreasing deque of indices. The front of the deque always holds the
 * index of the current window maximum.</p>
 * <p>Time: O(n) | Space: O(k)</p>
 */
public class LC239_SlidingWindowMaximum_Hard_SlidingWindow {

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];

        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3))); // [3,3,5,5,6,7]
        System.out.println(Arrays.toString(
                maxSlidingWindow(new int[]{1}, 1)));                          // [1]
    }
}
