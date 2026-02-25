package com.leetcode.easy.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LC155: Min Stack
 * Difficulty: Easy | Google: L3
 * Type: Stack, Design
 * Tags: stack, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.</p>
 * <p>Implement the MinStack class:</p>
 * <ul>
 *   <li>MinStack() - initializes the stack object</li>
 *   <li>void push(int val) - pushes val onto the stack</li>
 *   <li>void pop() - removes the element on the top</li>
 *   <li>int top() - gets the top element</li>
 *   <li>int getMin() - retrieves the minimum element in the stack</li>
 * </ul>
 * <p>You must implement a solution with O(1) time complexity for each function.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>-2³¹ ≤ val ≤ 2³¹ - 1</li>
 *   <li>pop, top, getMin will be called on non-empty stacks only</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example:
 *   Input:  ["MinStack","push","push","push","getMin","pop","top","getMin"]
 *           [[],[-2],[0],[-3],[],[],[],[]]
 *   Output: [null,null,null,null,-3,null,0,-2]
 *   Explanation: push(-2), push(0), push(-3) → getMin()=-3, pop() → top()=0, getMin()=-2
 * </pre>
 *
 * <p>Approach: Two stacks - one for values, one for min at each level.</p>
 * <p>Time: O(1) all ops | Space: O(n)</p>
 */
public class LC155_MinStack_Easy_Stack {

    private final Deque<Integer> stack = new ArrayDeque<>();
    private final Deque<Integer> minStack = new ArrayDeque<>();

    public void push(int val) {
        stack.push(val);
        minStack.push(minStack.isEmpty() ? val : Math.min(minStack.peek(), val));
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() { return stack.peek(); }
    public int getMin() { return minStack.peek(); }

    public static void main(String[] args) {
        LC155_MinStack_Easy_Stack st = new LC155_MinStack_Easy_Stack();
        st.push(-2); st.push(0); st.push(-3);
        System.out.println(st.getMin()); // -3
        st.pop();
        System.out.println(st.top());    // 0
        System.out.println(st.getMin()); // -2
    }
}
