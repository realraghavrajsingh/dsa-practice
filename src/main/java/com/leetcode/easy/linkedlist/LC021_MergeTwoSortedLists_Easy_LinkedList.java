package com.leetcode.easy.linkedlist;

/**
 * LC021: Merge Two Sorted Lists
 * Difficulty: Easy | Google: L3
 * Type: Linked List
 * Tags: linkedlist, faang
 *
 * <h2>Problem Statement</h2>
 * <p>You are given the heads of two sorted linked lists {@code list1} and {@code list2}. Merge the two
 * lists into one <b>sorted</b> list. The list should be made by splicing together the nodes of the first
 * two lists. Return the head of the merged linked list.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>The number of nodes in both lists is in the range [0, 50].</li>
 *   <li>-100 ≤ Node.val ≤ 100</li>
 *   <li>Both list1 and list2 are sorted in non-decreasing order.</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  list1 = [1,2,4], list2 = [1,3,4]
 *   Output: [1,1,2,3,4,4]
 *   Explanation: Merged sorted list.
 *
 * Example 2:
 *   Input:  list1 = [], list2 = []
 *   Output: []
 *
 * Example 3:
 *   Input:  list1 = [], list2 = [0]
 *   Output: [0]
 * </pre>
 *
 * <p>Approach: Dummy node, compare and link.</p>
 * <p>Time: O(m+n) | Space: O(1)</p>
 */
public class LC021_MergeTwoSortedLists_Easy_LinkedList {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) { tail.next = l1; l1 = l1.next; }
            else { tail.next = l2; l2 = l2.next; }
            tail = tail.next;
        }
        tail.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode merged = mergeTwoLists(l1, l2);
        while (merged != null) {
            System.out.print(merged.val + " ");
            merged = merged.next;
        }
        System.out.println(); // 1 1 2 3 4 4
    }
}
