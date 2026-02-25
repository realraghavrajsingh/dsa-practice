package com.leetcode.medium.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * LC039: Combination Sum
 * Difficulty: Medium | Google: L4
 * Type: Backtracking
 * Tags: backtracking, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Given an array of <b>distinct</b> integers {@code candidates} and a target integer {@code target},
 * return a list of all <b>unique combinations</b> of {@code candidates} where the chosen numbers sum
 * to {@code target}. You may return the combinations in any order.</p>
 * <p>The <b>same</b> number may be chosen from {@code candidates} an <b>unlimited number of times</b>.
 * Two combinations are unique if the frequency of at least one of the chosen numbers is different.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>1 ≤ candidates.length ≤ 30</li>
 *   <li>2 ≤ candidates[i] ≤ 40</li>
 *   <li>All elements of candidates are distinct.</li>
 *   <li>1 ≤ target ≤ 40</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  candidates = [2, 3, 6, 7], target = 7
 *   Output: [[2, 2, 3], [7]]
 *   Explanation: 2+2+3=7, 7=7
 *
 * Example 2:
 *   Input:  candidates = [2, 3, 5], target = 8
 *   Output: [[2, 2, 2, 2], [2, 3, 3], [3, 5]]
 *
 * Example 3:
 *   Input:  candidates = [2], target = 1
 *   Output: []
 * </pre>
 *
 * <p>Approach: Backtrack with start index to avoid duplicates.</p>
 * <p>Time: O(2^target) | Space: O(target)</p>
 */
public class LC039_CombinationSum_Medium_Backtracking {

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] cand, int remain, int start, List<Integer> path, List<List<Integer>> result) {
        if (remain == 0) { result.add(new ArrayList<>(path)); return; }
        if (remain < 0) return;
        for (int i = start; i < cand.length; i++) {
            path.add(cand[i]);
            backtrack(cand, remain - cand[i], i, path, result);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{2, 3, 6, 7}, 7));
        // [[2, 2, 3], [7]]
    }
}
