package com.leetcode.hard.heap;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * LC295: Find Median from Data Stream
 * Difficulty: Hard | Google: L5
 * Type: Heap / Priority Queue, Design
 * Tags: heap, design, faang
 *
 * <h2>Problem Statement</h2>
 * <p>The <b>median</b> is the middle value in an ordered integer list. If the size is even, the median
 * is the mean of the two middle values. Implement the {@code MedianFinder} class:
 * <ul>
 *   <li>{@code MedianFinder()} — initialises the object.</li>
 *   <li>{@code void addNum(int num)} — adds the integer to the data structure.</li>
 *   <li>{@code double findMedian()} — returns the median of all elements so far.</li>
 * </ul></p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>-10⁵ ≤ num ≤ 10⁵</li>
 *   <li>There will be at least one element before calling findMedian.</li>
 *   <li>At most 5 × 10⁴ calls to addNum and findMedian.</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  ["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
 *           [[],[1],[2],[],[3],[]]
 *   Output: [null,null,null,1.5,null,2.0]
 *   Explanation: After addNum(1) and addNum(2), median = (1+2)/2 = 1.5.
 *                After addNum(3), median = 2.0.
 * </pre>
 *
 * <p>Approach: Two heaps — a max-heap for the lower half and a min-heap for the upper half.
 * Keep them balanced so the median is always available from the heap tops.</p>
 * <p>Time: O(log n) addNum, O(1) findMedian | Space: O(n)</p>
 */
public class LC295_FindMedianFromDataStream_Hard_Heap {

    private final PriorityQueue<Integer> lo; // max-heap: lower half
    private final PriorityQueue<Integer> hi; // min-heap: upper half

    public LC295_FindMedianFromDataStream_Hard_Heap() {
        lo = new PriorityQueue<>(Collections.reverseOrder());
        hi = new PriorityQueue<>();
    }

    public void addNum(int num) {
        lo.offer(num);
        hi.offer(lo.poll());
        if (hi.size() > lo.size()) {
            lo.offer(hi.poll());
        }
    }

    public double findMedian() {
        if (lo.size() > hi.size()) return lo.peek();
        return (lo.peek() + hi.peek()) / 2.0;
    }

    public static void main(String[] args) {
        LC295_FindMedianFromDataStream_Hard_Heap mf = new LC295_FindMedianFromDataStream_Hard_Heap();
        mf.addNum(1);
        mf.addNum(2);
        System.out.println(mf.findMedian()); // 1.5
        mf.addNum(3);
        System.out.println(mf.findMedian()); // 2.0
    }
}
