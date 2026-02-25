package com.leetcode.medium.tree;

/**
 * LC098: Validate Binary Search Tree
 * Difficulty: Medium | Google: L4
 * Type: Tree, DFS
 * Tags: tree, dfs, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Given the {@code root} of a binary tree, determine if it is a valid binary search tree (BST). A
 * valid BST is defined as follows: (1) The left subtree of a node contains only nodes with keys less
 * than the node's key. (2) The right subtree of a node contains only nodes with keys greater than the
 * node's key. (3) Both the left and right subtrees must also be binary search trees.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>The number of nodes in the tree is in the range [1, 10⁴].</li>
 *   <li>-2³¹ ≤ Node.val ≤ 2³¹ - 1</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  root = [2, 1, 3]
 *      2
 *     / \
 *    1   3
 *   Output: true
 *
 * Example 2:
 *   Input:  root = [5, 1, 4, null, null, 3, 6]
 *      5
 *     / \
 *    1   4
 *       / \
 *      3   6
 *   Output: false
 *   Explanation: Root 5's right child 4 has left child 3 < 5. Invalid BST.
 * </pre>
 *
 * <p>Approach: DFS with min/max bounds.</p>
 * <p>Time: O(n) | Space: O(h)</p>
 */
public class LC098_ValidateBST_Medium_Tree {

    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static boolean isValidBST(TreeNode root) {
        return validate(root, null, null);
    }

    private static boolean validate(TreeNode node, Integer min, Integer max) {
        if (node == null) return true;
        if ((min != null && node.val <= min) || (max != null && node.val >= max)) return false;
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }

    public static void main(String[] args) {
        TreeNode valid = new TreeNode(2);
        valid.left = new TreeNode(1);
        valid.right = new TreeNode(3);
        System.out.println(isValidBST(valid)); // true

        TreeNode invalid = new TreeNode(5);
        invalid.left = new TreeNode(1);
        invalid.right = new TreeNode(4);
        invalid.right.left = new TreeNode(3);
        invalid.right.right = new TreeNode(6);
        System.out.println(isValidBST(invalid)); // false
    }
}
