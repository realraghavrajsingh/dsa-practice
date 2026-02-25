package com.leetcode.hard.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LC084: Largest Rectangle in Histogram
 * Difficulty: Hard | Google: L5
 * Type: Stack, Array
 * Tags: stack, array, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Given an array of integers {@code heights} representing the histogram's bar height where the width
 * of each bar is 1, return the area of the <b>largest rectangle</b> in the histogram.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>1 ≤ heights.length ≤ 10⁵</li>
 *   <li>0 ≤ heights[i] ≤ 10⁴</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  heights = [2,1,5,6,2,3]
 *   Output: 10
 *   Explanation: The largest rectangle has area = 10 (height 5 spanning indices 2–3).
 *
 * Example 2:
 *   Input:  heights = [2,4]
 *   Output: 4
 * </pre>
 *
 * <p>Approach: Monotonic increasing stack of indices. When a bar shorter than the stack top is
 * encountered, pop and compute the area with the popped bar as the shortest bar in the range.</p>
 * <p>Time: O(n) | Space: O(n)</p>
 */
public class LC084_LargestRectangleInHistogram_Hard_Stack {

    public static int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {
            int curHeight = (i == n) ? 0 : heights[i];
            while (!stack.isEmpty() && curHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println(largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3})); // 10
        System.out.println(largestRectangleArea(new int[]{2, 4}));              // 4
    }
}
