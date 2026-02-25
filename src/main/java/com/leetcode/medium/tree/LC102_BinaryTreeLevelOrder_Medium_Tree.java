package com.leetcode.medium.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LC102: Binary Tree Level Order Traversal
 * Difficulty: Medium | Google: L4
 * Type: Tree, BFS
 * Tags: tree, bfs, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Given the {@code root} of a binary tree, return the <b>level order</b> traversal of its nodes'
 * values. (i.e., from left to right, level by level).</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>The number of nodes in the tree is in the range [0, 2000].</li>
 *   <li>-1000 ≤ Node.val ≤ 1000</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  root = [3, 9, 20, null, null, 15, 7]
 *      3
 *     / \
 *    9  20
 *      /  \
 *     15   7
 *   Output: [[3], [9, 20], [15, 7]]
 *
 * Example 2:
 *   Input:  root = [1]
 *   Output: [[1]]
 *
 * Example 3:
 *   Input:  root = []
 *   Output: []
 * </pre>
 *
 * <p>Approach: BFS with queue.</p>
 * <p>Time: O(n) | Space: O(n)</p>
 */
public class LC102_BinaryTreeLevelOrder_Medium_Tree {

    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val; this.left = left; this.right = right;
        }
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                level.add(node.val);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            result.add(level);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println(levelOrder(root)); // [[3], [9, 20], [15, 7]]
    }
}
