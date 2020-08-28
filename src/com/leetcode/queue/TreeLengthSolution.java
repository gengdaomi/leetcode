package com.leetcode.queue;

/**
 * 题目3
 *
 * //评测题目3：3. 给定一个二叉树，找出其最大深度。二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * //示例：
 * //给定二叉树 [3,9,20,null,null,15,7]，
 * //    3
 * //   / \
 * //  9  20
 * //    /  \
 * //   15   7
 * //返回它的最大深度 3
 *
 * Created By Andrew-Geng on 2020/8/28 9:01 下午
 */
public class TreeLengthSolution {

    private int result = 0;

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        result = Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;

        return result;
    }

    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }
}
