package com.leetcode.hard.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * LC297: Serialize and Deserialize Binary Tree
 * Difficulty: Hard | Google: L5
 * Type: Tree, BFS/DFS
 * Tags: tree, design, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Serialization is the process of converting a data structure or object into a sequence of bits so that
 * it can be stored in a file or memory buffer, or transmitted across a network connection link to be
 * reconstructed later in the same or another computer environment. Design an algorithm to serialize and
 * deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm
 * should work. You just need to ensure that a binary tree can be serialized to a string and this string can
 * be deserialized to the original tree structure.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>The number of nodes in the tree is in the range [0, 10⁴].</li>
 *   <li>-1000 ≤ Node.val ≤ 1000</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  root = [1, 2, 3, null, null, 4, 5]
 *   Output: Serialized string (e.g. "1,2,3,null,null,4,5,")
 *   Deserialize back to original tree.
 *
 * Example 2:
 *   Input:  root = []
 *   Output: "" (empty string for null tree)
 * </pre>
 *
 * <p>Approach: BFS level-order with "null" for missing nodes.</p>
 * <p>Time: O(n) | Space: O(n)</p>
 */
public class LC297_SerializeDeserializeBinaryTree_Hard_Tree {

    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == null) sb.append("null,");
            else {
                sb.append(node.val).append(",");
                q.offer(node.left);
                q.offer(node.right);
            }
        }
        return sb.toString();
    }

    public static TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;
        Queue<String> vals = new LinkedList<>(Arrays.asList(data.split(",")));
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(vals.poll()));
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            String left = vals.poll();
            String right = vals.poll();
            if (!"null".equals(left)) {
                node.left = new TreeNode(Integer.parseInt(left));
                q.offer(node.left);
            }
            if (!"null".equals(right)) {
                node.right = new TreeNode(Integer.parseInt(right));
                q.offer(node.right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        String s = serialize(root);
        System.out.println(s);
        TreeNode restored = deserialize(s);
        System.out.println(restored.val + " " + restored.left.val + " " + restored.right.val);
    }
}
