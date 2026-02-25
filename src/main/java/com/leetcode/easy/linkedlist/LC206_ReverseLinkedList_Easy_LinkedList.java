package com.leetcode.easy.linkedlist;

/**
 * LC206: Reverse Linked List
 * Difficulty: Easy | Google: L3
 * Type: Linked List
 * Tags: linkedlist, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Given the {@code head} of a singly linked list, reverse the list, and return the <b>reversed list</b>.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>The number of nodes in the list is the range [0, 5000].</li>
 *   <li>-5000 ≤ Node.val ≤ 5000</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  head = [1,2,3,4,5]
 *   Output: [5,4,3,2,1]
 *
 * Example 2:
 *   Input:  head = [1,2]
 *   Output: [2,1]
 *
 * Example 3:
 *   Input:  head = []
 *   Output: []
 * </pre>
 *
 * <p>Approach: Iterative with prev/curr/next pointers.</p>
 * <p>Time: O(n) | Space: O(1)</p>
 */
public class LC206_ReverseLinkedList_Easy_LinkedList {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3)));
        ListNode reversed = reverseList(head);
        while (reversed != null) {
            System.out.print(reversed.val + " ");
            reversed = reversed.next;
        }
        System.out.println(); // 3 2 1
    }
}
