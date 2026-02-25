package com.leetcode.easy.string;

/**
 * LC344: Reverse String
 * Difficulty: Easy | Google: L3
 * Type: String, Two Pointers
 * Tags: string, twopointers, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Write a function that reverses a string. The input string is given as an array of characters
 * {@code s}. You must do this by modifying the input array <b>in-place</b> with O(1) extra memory.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>1 ≤ s.length ≤ 10⁵</li>
 *   <li>s[i] is a printable ascii character.</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  s = ["h","e","l","l","o"]
 *   Output: ["o","l","l","e","h"]
 *
 * Example 2:
 *   Input:  s = ["H","a","n","n","a","h"]
 *   Output: ["h","a","n","n","a","H"]
 * </pre>
 *
 * <p>Approach: Two pointers swap from both ends.</p>
 * <p>Time: O(n) | Space: O(1)</p>
 */
public class LC344_ReverseString_Easy_String {

    public static void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        while (left < right) {
            char t = s[left];
            s[left] = s[right];
            s[right] = t;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        char[] s = "hello".toCharArray();
        reverseString(s);
        System.out.println(new String(s)); // olleh
    }
}
