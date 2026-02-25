package com.leetcode.hard.linkedlist;

import java.util.PriorityQueue;

/**
 * LC023: Merge K Sorted Lists
 * Difficulty: Hard | Google: L5
 * Type: Linked List, Heap
 * Tags: linkedlist, heap, faang
 *
 * <h2>Problem Statement</h2>
 * <p>You are given an array of {@code k} linked-lists {@code lists}, each linked-list is sorted in
 * ascending order. Merge all the linked-lists into one sorted linked-list and return it.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>k == lists.length</li>
 *   <li>0 ≤ k ≤ 10⁴</li>
 *   <li>0 ≤ lists[i].length ≤ 500</li>
 *   <li>-10⁴ ≤ lists[i][j] ≤ 10⁴</li>
 *   <li>lists[i] is sorted in ascending order.</li>
 *   <li>The sum of lists[i].length will not exceed 10⁴.</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  lists = [[1,4,5],[1,3,4],[2,6]]
 *   Output: [1,1,2,3,4,4,5,6]
 *
 * Example 2:
 *   Input:  lists = []
 *   Output: []
 *
 * Example 3:
 *   Input:  lists = [[]]
 *   Output: []
 * </pre>
 *
 * <p>Approach: Min-heap holding one node from each list. Poll the smallest, advance its pointer,
 * push its next back into the heap.</p>
 * <p>Time: O(N log k) where N = total nodes | Space: O(k)</p>
 */
public class LC023_MergeKSortedLists_Hard_LinkedList {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode head : lists) {
            if (head != null) heap.offer(head);
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (!heap.isEmpty()) {
            ListNode node = heap.poll();
            tail.next = node;
            tail = tail.next;
            if (node.next != null) heap.offer(node.next);
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode l3 = new ListNode(2, new ListNode(6));

        ListNode result = mergeKLists(new ListNode[]{l1, l2, l3});
        StringBuilder sb = new StringBuilder("[");
        while (result != null) {
            sb.append(result.val);
            if (result.next != null) sb.append(",");
            result = result.next;
        }
        sb.append("]");
        System.out.println(sb); // [1,1,2,3,4,4,5,6]
    }
}
