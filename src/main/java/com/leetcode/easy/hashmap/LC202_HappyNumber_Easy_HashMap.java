package com.leetcode.easy.hashmap;

import java.util.HashSet;
import java.util.Set;

/**
 * LC202: Happy Number
 * Difficulty: Easy | Google: L3
 * Type: Hash Map, Math
 * Tags: hashmap, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Write an algorithm to determine if a number {@code n} is happy.</p>
 * <p>A <b>happy number</b> is a number defined by the following process: Starting with any positive integer,
 * replace the number by the sum of the squares of its digits. Repeat the process until the number equals 1
 * (where it will stay), or it <b>loops endlessly in a cycle</b> which does not include 1. Those numbers for
 * which this process ends in 1 are happy.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>1 ≤ n ≤ 2³¹ - 1</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  n = 19
 *   Output: true
 *   Explanation: 1² + 9² = 82 → 8² + 2² = 68 → 6² + 8² = 100 → 1² + 0² + 0² = 1
 *
 * Example 2:
 *   Input:  n = 2
 *   Output: false
 *   Explanation: 2 → 4 → 16 → 37 → 58 → 89 → 145 → 42 → 20 → 4 (cycle, never reaches 1)
 * </pre>
 *
 * <p>Approach: Set to detect cycle.</p>
 * <p>Time: O(log n) | Space: O(log n)</p>
 */
public class LC202_HappyNumber_Easy_HashMap {

    public static boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = sumSquares(n);
        }
        return n == 1;
    }

    private static int sumSquares(int n) {
        int sum = 0;
        while (n > 0) {
            int d = n % 10;
            sum += d * d;
            n /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(isHappy(19));  // true
        System.out.println(isHappy(2));   // false
    }
}
