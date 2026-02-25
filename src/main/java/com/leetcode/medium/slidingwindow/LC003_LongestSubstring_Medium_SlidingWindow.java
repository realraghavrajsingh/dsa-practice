package com.leetcode.medium.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * LC003: Longest Substring Without Repeating Characters
 * Difficulty: Medium | Google: L4
 * Type: Sliding Window, Hash Map
 * Tags: slidingwindow, hashmap, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Given a string {@code s}, find the length of the <b>longest substring</b> without repeating characters.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>0 ≤ s.length ≤ 5 × 10⁴</li>
 *   <li>s consists of English letters, digits, symbols and spaces.</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  s = "abcabcbb"
 *   Output: 3
 *   Explanation: The answer is "abc", with length 3.
 *
 * Example 2:
 *   Input:  s = "bbbbb"
 *   Output: 1
 *   Explanation: The answer is "b", with length 1.
 *
 * Example 3:
 *   Input:  s = "pwwkew"
 *   Output: 3
 *   Explanation: The answer is "wke" or "kew", length 3.
 * </pre>
 *
 * <p>Approach: Sliding window with char->index map.</p>
 * <p>Time: O(n) | Space: O(min(n, charset))</p>
 */
public class LC003_LongestSubstring_Medium_SlidingWindow {

    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int max = 0, start = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c) && map.get(c) >= start)
                start = map.get(c) + 1;
            map.put(c, i);
            max = Math.max(max, i - start + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb")); // 3
        System.out.println(lengthOfLongestSubstring("bbbbb"));  // 1
        System.out.println(lengthOfLongestSubstring("pwwkew"));  // 3
    }
}
