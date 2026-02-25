package com.leetcode.easy.bitmanipulation;

/**
 * LC191: Number of 1 Bits (Hamming Weight)
 * Difficulty: Easy | Google: L3
 * Type: Bit Manipulation
 * Tags: bitmanipulation, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Write a function that takes the binary representation of an unsigned integer and returns the number
 * of '1' bits it has (also known as the Hamming weight).</p>
 * <p>Note that in some languages, such as Java, there is no unsigned integer type. In this case, the input
 * will be given as a signed integer type. It should not affect your implementation, as the integer's
 * internal binary representation is the same, whether it is signed or unsigned.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>The input must be a binary string of length 32.</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  n = 11 (binary: 00000000000000000000000000001011)
 *   Output: 3
 *   Explanation: The input has three '1' bits.
 *
 * Example 2:
 *   Input:  n = 128 (binary: 00000000000000000000000010000000)
 *   Output: 1
 *
 * Example 3:
 *   Input:  n = 2147483645 (binary: 01111111111111111111111111111101)
 *   Output: 31
 * </pre>
 *
 * <p>Approach: n & (n-1) clears lowest set bit.</p>
 * <p>Time: O(k) k=number of 1 bits | Space: O(1)</p>
 */
public class LC191_NumberOfOneBits_Easy_BitManipulation {

    public static int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(hammingWeight(11));   // 3 (1011)
        System.out.println(hammingWeight(128));  // 1
    }
}
