package com.leetcode.medium.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC015: 3Sum
 * Difficulty: Medium | Google: L4
 * Type: Array, Two Pointers
 * Tags: array, twopointers, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Given an integer array {@code nums}, return all the triplets {@code [nums[i], nums[j], nums[k]]}
 * such that {@code i != j}, {@code i != k}, {@code j != k}, and {@code nums[i] + nums[j] + nums[k] == 0}.</p>
 * <p>Notice that the solution set must not contain duplicate triplets.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>3 ≤ nums.length ≤ 3000</li>
 *   <li>-10⁵ ≤ nums[i] ≤ 10⁵</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  nums = [-1, 0, 1, 2, -1, -4]
 *   Output: [[-1, -1, 2], [-1, 0, 1]]
 *   Explanation: nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 *                nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 *                nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 *
 * Example 2:
 *   Input:  nums = [0, 1, 1]
 *   Output: []
 *
 * Example 3:
 *   Input:  nums = [0, 0, 0]
 *   Output: [[0, 0, 0]]
 * </pre>
 *
 * <p>Approach: Sort + two pointers for each fixed first element.</p>
 * <p>Time: O(n²) | Space: O(1) excluding output</p>
 */
public class LC015_ThreeSum_Medium_Array {

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(List.of(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (sum < 0) left++;
                else right--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        // [[-1, -1, 2], [-1, 0, 1]]
    }
}
