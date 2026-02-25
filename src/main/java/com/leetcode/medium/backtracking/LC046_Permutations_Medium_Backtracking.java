package com.leetcode.medium.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * LC046: Permutations
 * Difficulty: Medium | Google: L4
 * Type: Backtracking
 * Tags: backtracking, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Given an array {@code nums} of distinct integers, return all the possible permutations. You can
 * return the answer in any order.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>1 ≤ nums.length ≤ 6</li>
 *   <li>-10 ≤ nums[i] ≤ 10</li>
 *   <li>All integers of nums are unique.</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  nums = [1, 2, 3]
 *   Output: [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]
 *
 * Example 2:
 *   Input:  nums = [0, 1]
 *   Output: [[0,1], [1,0]]
 *
 * Example 3:
 *   Input:  nums = [1]
 *   Output: [[1]]
 * </pre>
 *
 * <p>Approach: Backtrack with swap.</p>
 * <p>Time: O(n! * n) | Space: O(n)</p>
 */
public class LC046_Permutations_Medium_Backtracking {

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, result);
        return result;
    }

    private static void backtrack(int[] nums, int start, List<List<Integer>> result) {
        if (start == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int n : nums) list.add(n);
            result.add(list);
            return;
        }
        for (int i = start; i < nums.length; i++) {
            swap(nums, start, i);
            backtrack(nums, start + 1, result);
            swap(nums, start, i);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int t = nums[i]; nums[i] = nums[j]; nums[j] = t;
    }

    public static void main(String[] args) {
        System.out.println(permute(new int[]{1, 2, 3}));
        // [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]
    }
}
