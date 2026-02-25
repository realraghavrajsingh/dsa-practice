package com.leetcode.easy.twopointers;

/**
 * LC125: Valid Palindrome
 * Difficulty: Easy | Google: L3
 * Type: Two Pointers, String
 * Tags: twopointers, string, faang
 *
 * <h2>Problem Statement</h2>
 * <p>A phrase is a <b>palindrome</b> if, after converting all uppercase letters into lowercase letters
 * and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric
 * characters include letters and numbers.</p>
 * <p>Given a string {@code s}, return {@code true} if it is a palindrome, or {@code false} otherwise.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>1 ≤ s.length ≤ 2 × 10⁵</li>
 *   <li>s consists only of printable ASCII characters.</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  s = "A man, a plan, a canal: Panama"
 *   Output: true
 *   Explanation: "amanaplanacanalpanama" is a palindrome.
 *
 * Example 2:
 *   Input:  s = "race a car"
 *   Output: false
 *   Explanation: "raceacar" is not a palindrome.
 *
 * Example 3:
 *   Input:  s = " "
 *   Output: true
 *   Explanation: Empty string after removal is a palindrome.
 * </pre>
 *
 * <p>Approach: Two pointers from both ends.</p>
 * <p>Time: O(n) | Space: O(1)</p>
 */
public class LC125_ValidPalindrome_Easy_TwoPointers {

    public static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) left++;
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) right--;
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right)))
                return false;
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama")); // true
        System.out.println(isPalindrome("race a car"));                     // false
    }
}
