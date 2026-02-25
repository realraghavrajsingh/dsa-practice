package com.leetcode.hard.tree;

/**
 * LC124: Binary Tree Maximum Path Sum
 * Difficulty: Hard | Google: L5
 * Type: Tree, DFS
 * Tags: tree, dfs, faang
 *
 * <h2>Problem Statement</h2>
 * <p>A <b>path</b> in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence
 * has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does
 * not need to pass through the root. The <b>path sum</b> of a path is the sum of the node's values in the path.</p>
 * <p>Given the {@code root} of a binary tree, return the maximum path sum of any non-empty path.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>The number of nodes in the tree is in the range [1, 3 × 10⁴].</li>
 *   <li>-1000 ≤ Node.val ≤ 1000</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  root = [1, 2, 3]
 *      1
 *     / \
 *    2   3
 *   Output: 6
 *   Explanation: Optimal path is 2 → 1 → 3 with sum 6.
 *
 * Example 2:
 *   Input:  root = [-10, 9, 20, null, null, 15, 7]
 *      -10
 *      /  \
 *     9   20
 *        /  \
 *       15   7
 *   Output: 42
 *   Explanation: Optimal path is 15 → 20 → 7 with sum 42.
 * </pre>
 *
 * <p>Approach: DFS; return max path through node (single branch); track global max.</p>
 * <p>Time: O(n) | Space: O(h)</p>
 */
public class LC124_BinaryTreeMaxPathSum_Hard_Tree {

    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val; this.left = left; this.right = right;
        }
    }

    private static int maxSum;

    public static int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        gain(root);
        return maxSum;
    }

    private static int gain(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, gain(node.left));
        int right = Math.max(0, gain(node.right));
        maxSum = Math.max(maxSum, node.val + left + right);
        return node.val + Math.max(left, right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-10, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println(maxPathSum(root)); // 42
    }
}
