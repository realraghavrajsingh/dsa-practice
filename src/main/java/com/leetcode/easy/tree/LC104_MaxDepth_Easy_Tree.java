package com.leetcode.easy.tree;

/**
 * LC104: Maximum Depth of Binary Tree
 * Difficulty: Easy | Google: L3
 * Type: Tree, DFS
 * Tags: tree, dfs, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Given the {@code root} of a binary tree, return its maximum depth.</p>
 * <p>A binary tree's <b>maximum depth</b> is the number of nodes along the longest path from the root
 * node down to the farthest leaf node.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>The number of nodes in the tree is in the range [0, 10⁴].</li>
 *   <li>-100 ≤ Node.val ≤ 100</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  root = [3,9,20,null,null,15,7]
 *           3
 *          / \
 *         9  20
 *            / \
 *           15  7
 *   Output: 3
 *   Explanation: Longest path: 3 → 20 → 15 (or 7), depth = 3.
 *
 * Example 2:
 *   Input:  root = [1,null,2]
 *   Output: 2
 * </pre>
 *
 * <p>Approach: Recursive DFS.</p>
 * <p>Time: O(n) | Space: O(h) - recursion stack</p>
 */
public class LC104_MaxDepth_Easy_Tree {

    public static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public static void main(String[] args) {
        TreeNode node9 = new TreeNode(9);
        TreeNode node15 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);
        TreeNode node20 = new TreeNode(20, node15, node7);
        TreeNode root = new TreeNode(3, node9, node20);

        System.out.println(maxDepth(root)); // 3
    }
}
