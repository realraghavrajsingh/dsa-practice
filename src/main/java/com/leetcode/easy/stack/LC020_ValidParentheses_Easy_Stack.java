package com.leetcode.easy.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * LC020: Valid Parentheses
 * Difficulty: Easy | Google: L3
 * Type: Stack, String
 * Tags: stack, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Given a string {@code s} containing just the characters '(', ')', '{', '}', '[' and ']', determine
 * if the input string is valid.</p>
 * <p>An input string is valid if: (1) Open brackets must be closed by the same type of brackets.
 * (2) Open brackets must be closed in the correct order. (3) Every close bracket has a corresponding
 * open bracket of the same type.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>1 ≤ s.length ≤ 10⁴</li>
 *   <li>s consists of parentheses only '()[]{}'.</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  s = "()"
 *   Output: true
 *
 * Example 2:
 *   Input:  s = "()[]{}"
 *   Output: true
 *
 * Example 3:
 *   Input:  s = "(]"
 *   Output: false
 *   Explanation: ')' and ']' don't match.
 *
 * Example 4:
 *   Input:  s = "([)]"
 *   Output: false
 *   Explanation: Wrong nesting order.
 * </pre>
 *
 * <p>Approach: Stack to match opening brackets.</p>
 * <p>Time: O(n) | Space: O(n)</p>
 */
public class LC020_ValidParentheses_Easy_Stack {

    private static final Map<Character, Character> PAIRS = Map.of(')', '(', ']', '[', '}', '{');

    public static boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (PAIRS.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != PAIRS.get(c)) return false;
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("()[]{}")); // true
        System.out.println(isValid("(]"));      // false
    }
}
