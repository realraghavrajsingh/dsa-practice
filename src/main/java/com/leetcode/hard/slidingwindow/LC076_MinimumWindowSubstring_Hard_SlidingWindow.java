package com.leetcode.hard.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * LC076: Minimum Window Substring
 * Difficulty: Hard | Google: L5
 * Type: Sliding Window, Hash Map
 * Tags: slidingwindow, hashmap, string, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Given two strings {@code s} and {@code t} of lengths m and n respectively, return the <b>minimum
 * window substring</b> of {@code s} such that every character in {@code t} (including duplicates) is
 * included in the window. If there is no such substring, return the empty string {@code ""}.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>m == s.length, n == t.length</li>
 *   <li>1 ≤ m, n ≤ 10⁵</li>
 *   <li>s and t consist of uppercase and lowercase English letters.</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  s = "ADOBECODEBANC", t = "ABC"
 *   Output: "BANC"
 *   Explanation: "BANC" is the smallest window in s that contains all characters of t.
 *
 * Example 2:
 *   Input:  s = "a", t = "a"
 *   Output: "a"
 *
 * Example 3:
 *   Input:  s = "a", t = "aa"
 *   Output: ""
 *   Explanation: Both 'a's from t must be in the window, but s has only one 'a'.
 * </pre>
 *
 * <p>Approach: Expand right pointer to satisfy the window, then shrink left pointer to minimise.
 * Track how many characters of t are satisfied with a frequency map and a "formed" counter.</p>
 * <p>Time: O(|s| + |t|) | Space: O(|s| + |t|)</p>
 */
public class LC076_MinimumWindowSubstring_Hard_SlidingWindow {

    public static String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) return "";

        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) need.merge(c, 1, Integer::sum);

        int required = need.size();
        int formed = 0;
        Map<Character, Integer> windowCounts = new HashMap<>();

        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int minLeft = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            windowCounts.merge(c, 1, Integer::sum);

            if (need.containsKey(c) && windowCounts.get(c).intValue() == need.get(c).intValue()) {
                formed++;
            }

            while (formed == required) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minLeft = left;
                }
                char leftChar = s.charAt(left);
                windowCounts.merge(leftChar, -1, Integer::sum);
                if (need.containsKey(leftChar)
                        && windowCounts.get(leftChar) < need.get(leftChar)) {
                    formed--;
                }
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLen);
    }

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC")); // BANC
        System.out.println(minWindow("a", "a"));               // a
        System.out.println(minWindow("a", "aa"));              // ""
    }
}
