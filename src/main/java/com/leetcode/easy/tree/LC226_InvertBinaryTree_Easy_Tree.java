package com.leetcode.easy.tree;

/**
 * LC226: Invert Binary Tree
 * Difficulty: Easy | Google: L3
 * Type: Tree, DFS
 * Tags: tree, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Given the {@code root} of a binary tree, invert the tree, and return its root.</p>
 * <p>To invert a binary tree: swap the left and right child of every node.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>The number of nodes in the tree is in the range [0, 100].</li>
 *   <li>-100 ≤ Node.val ≤ 100</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  root = [4,2,7,1,3,6,9]
 *           4                   4
 *          / \                 / \
 *         2   7      →        7   2
 *        / \ / \             / \ / \
 *       1  3 6  9          9  6 3  1
 *   Output: [4,7,2,9,6,3,1]
 *
 * Example 2:
 *   Input:  root = [2,1,3]
 *   Output: [2,3,1]
 *
 * Example 3:
 *   Input:  root = []
 *   Output: []
 * </pre>
 *
 * <p>Approach: Recursive swap.</p>
 * <p>Time: O(n) | Space: O(h)</p>
 */
public class LC226_InvertBinaryTree_Easy_Tree {

    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val; this.left = left; this.right = right;
        }
    }

    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode t = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(t);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                new TreeNode(7, new TreeNode(6), new TreeNode(9)));
        invertTree(root);
        System.out.println(root.left.val + " " + root.right.val); // 7 2
    }
}
