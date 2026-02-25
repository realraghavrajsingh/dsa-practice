package com.leetcode.medium.design;

import java.util.HashMap;
import java.util.Map;

/**
 * LC146: LRU Cache
 * Difficulty: Medium | Google: L4
 * Type: Design, HashMap + Doubly Linked List
 * Tags: design, hashmap, linkedlist, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Design a data structure that follows the constraints of a <b>Least Recently Used (LRU) cache</b>.
 * Implement the {@code LRUCache} class:
 * <ul>
 *   <li>{@code LRUCache(int capacity)} — initialise with positive capacity.</li>
 *   <li>{@code int get(int key)} — return the value if the key exists, otherwise -1.</li>
 *   <li>{@code void put(int key, int value)} — update or insert. If capacity exceeded, evict the
 *       least recently used key before inserting.</li>
 * </ul>
 * Both operations must run in <b>O(1)</b> average time.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>1 ≤ capacity ≤ 3000</li>
 *   <li>0 ≤ key ≤ 10⁴</li>
 *   <li>0 ≤ value ≤ 10⁵</li>
 *   <li>At most 2 × 10⁵ calls to get and put.</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  ["LRUCache","put","put","get","put","get","put","get","get","get"]
 *           [[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
 *   Output: [null,null,null,1,null,-1,null,-1,3,4]
 * </pre>
 *
 * <p>Approach: HashMap for O(1) lookup + doubly-linked list for O(1) insertion/removal at
 * head/tail. Most-recently-used nodes are moved to the head; eviction removes from the tail.</p>
 * <p>Time: O(1) per operation | Space: O(capacity)</p>
 */
public class LC146_LRUCache_Medium_Design {

    private static class Node {
        int key, value;
        Node prev, next;
        Node(int key, int value) { this.key = key; this.value = value; }
    }

    private final int capacity;
    private final Map<Integer, Node> map;
    private final Node head, tail;

    public LC146_LRUCache_Medium_Design(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        remove(node);
        insertAtHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            remove(node);
            insertAtHead(node);
        } else {
            if (map.size() == capacity) {
                Node lru = tail.prev;
                remove(lru);
                map.remove(lru.key);
            }
            Node node = new Node(key, value);
            map.put(key, node);
            insertAtHead(node);
        }
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insertAtHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public static void main(String[] args) {
        LC146_LRUCache_Medium_Design cache = new LC146_LRUCache_Medium_Design(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));  // 1
        cache.put(3, 3);                   // evicts key 2
        System.out.println(cache.get(2));  // -1
        cache.put(4, 4);                   // evicts key 1
        System.out.println(cache.get(1));  // -1
        System.out.println(cache.get(3));  // 3
        System.out.println(cache.get(4));  // 4
    }
}
