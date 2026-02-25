package com.leetcode.medium.tree;

/**
 * LC236: Lowest Common Ancestor of a Binary Tree
 * Difficulty: Medium | Google: L4
 * Type: Tree, DFS
 * Tags: tree, dfs, recursion, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Given a binary tree, find the <b>lowest common ancestor (LCA)</b> of two given nodes {@code p}
 * and {@code q}. The LCA is defined as the lowest node in the tree that has both p and q as
 * descendants (where a node can be a descendant of itself).</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>The number of nodes in the tree is in the range [2, 10⁵].</li>
 *   <li>-10⁹ ≤ Node.val ≤ 10⁹</li>
 *   <li>All Node.val are unique.</li>
 *   <li>p ≠ q</li>
 *   <li>p and q will exist in the tree.</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 *   Output: 3
 *   Explanation: The LCA of nodes 5 and 1 is 3.
 *
 * Example 2:
 *   Input:  root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 *   Output: 5
 *   Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself.
 *
 * Example 3:
 *   Input:  root = [1,2], p = 1, q = 2
 *   Output: 1
 * </pre>
 *
 * <p>Approach: Post-order DFS. If current node is p or q, return it. Recurse left and right.
 * If both sides return non-null, current node is the LCA. Otherwise propagate the non-null side.</p>
 * <p>Time: O(n) | Space: O(n) — recursion stack</p>
 */
public class LC236_LowestCommonAncestor_Medium_Tree {

    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        return left != null ? left : right;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode n5 = new TreeNode(5);
        TreeNode n1 = new TreeNode(1);
        TreeNode n6 = new TreeNode(6);
        TreeNode n2 = new TreeNode(2);
        TreeNode n0 = new TreeNode(0);
        TreeNode n8 = new TreeNode(8);
        TreeNode n7 = new TreeNode(7);
        TreeNode n4 = new TreeNode(4);

        root.left = n5;  root.right = n1;
        n5.left = n6;    n5.right = n2;
        n1.left = n0;    n1.right = n8;
        n2.left = n7;    n2.right = n4;

        System.out.println(lowestCommonAncestor(root, n5, n1).val); // 3
        System.out.println(lowestCommonAncestor(root, n5, n4).val); // 5
    }
}
