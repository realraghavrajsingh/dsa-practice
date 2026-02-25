package com.leetcode.easy.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * LC242: Valid Anagram
 * Difficulty: Easy | Google: L3
 * Type: Hash Map, String
 * Tags: hashmap, string, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Given two strings {@code s} and {@code t}, return {@code true} if {@code t} is an anagram of
 * {@code s}, and {@code false} otherwise.</p>
 * <p>An <b>Anagram</b> is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>1 ≤ s.length, t.length ≤ 5 × 10⁴</li>
 *   <li>s and t consist of lowercase English letters.</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  s = "anagram", t = "nagaram"
 *   Output: true
 *   Explanation: Both strings contain the same letters: a,a,a,g,m,n,r
 *
 * Example 2:
 *   Input:  s = "rat", t = "car"
 *   Output: false
 *   Explanation: "rat" and "car" have different character counts.
 * </pre>
 *
 * <p>Approach: Count character frequencies in both strings.</p>
 * <p>Time: O(n) | Space: O(1) - fixed 26 chars</p>
 */
public class LC242_ValidAnagram_Easy_HashMap {

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Integer> count = new HashMap<>();
        for (char c : s.toCharArray()) count.merge(c, 1, Integer::sum);
        for (char c : t.toCharArray()) {
            count.merge(c, -1, Integer::sum);
            if (count.get(c) < 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isAnagram("anagram", "nagaram")); // true
        System.out.println(isAnagram("rat", "car"));         // false
    }
}
