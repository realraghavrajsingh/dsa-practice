package com.leetcode.easy.array;

import java.util.HashMap;
import java.util.Map;

/**
 * LC001: Two Sum
 * Difficulty: Easy | Google: L3
 * Type: Array, Hash Map
 * Tags: array, hashmap, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Given an array of integers {@code nums} and an integer {@code target}, return <b>indices</b> of the two
 * numbers such that they add up to {@code target}.</p>
 * <p>You may assume that each input would have <b>exactly one solution</b>, and you may not use the same
 * element twice. You can return the answer in any order.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>2 ≤ nums.length ≤ 10⁴</li>
 *   <li>-10⁹ ≤ nums[i] ≤ 10⁹</li>
 *   <li>-10⁹ ≤ target ≤ 10⁹</li>
 *   <li>Only one valid answer exists.</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  nums = [2, 7, 11, 15], target = 9
 *   Output: [0, 1]
 *   Explanation: nums[0] + nums[1] = 2 + 7 = 9, so we return [0, 1].
 *
 * Example 2:
 *   Input:  nums = [3, 2, 4], target = 6
 *   Output: [1, 2]
 *   Explanation: nums[1] + nums[2] = 2 + 4 = 6.
 *
 * Example 3:
 *   Input:  nums = [3, 3], target = 6
 *   Output: [0, 1]
 * </pre>
 *
 * <p>Approach: One-pass HashMap. Store complement (target - num) as we iterate.</p>
 * <p>Time: O(n) | Space: O(n)</p>
 */
public class LC001_TwoSum_Easy_Array {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (seen.containsKey(complement)) {
                return new int[]{seen.get(complement), i};
            }
            seen.put(nums[i], i);
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum(nums, target);
        System.out.println("Indices: [" + result[0] + ", " + result[1] + "]"); // [0, 1]
    }
}
