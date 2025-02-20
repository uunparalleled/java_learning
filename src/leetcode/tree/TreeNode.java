package leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode() {}
    public TreeNode(int val) { this.val = val; }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
    public TreeNode(Integer[] tree) {
        if (tree == null || tree.length == 0) {
            throw new IllegalArgumentException("输入不能为空");
        }
        // 创建根节点
        this.val = tree[0];

        // 使用队列来处理树的层次关系
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(this);

        int i = 1;
        while (i < tree.length) {
            TreeNode current = queue.poll();

            // 左子节点
            if (tree[i] != null) {
                current.left = new TreeNode(tree[i]);
                queue.offer(current.left);
            }
            i++;

            // 右子节点
            if (i < tree.length && tree[i] != null) {
                current.right = new TreeNode(tree[i]);
                queue.offer(current.right);
            }
            i++;
        }
    }

    // 按输入格式输出树
    public String toString() {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(this);

        // 标记最后一个非空节点的位置
        int lastNonNullIndex = 0;
        LinkedList<String> nodeValues = new LinkedList<>();

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                nodeValues.add(String.valueOf(node.val));
                lastNonNullIndex = nodeValues.size(); // 更新最后一个非空节点的索引
                queue.offer(node.left);
                queue.offer(node.right);
            } else {
                nodeValues.add("null");
            }
        }

        // 只保留到最后一个非null节点
        while (nodeValues.size() > lastNonNullIndex) {
            nodeValues.removeLast();
        }

        return "[" + String.join(",", nodeValues) + "]";
    }

    /**
     * 前序遍历
     * @param root
     */
    public static void preOrderRecur(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        preOrderRecur(root.left);
        preOrderRecur(root.right);
    }

    /**
     * 中序遍历
     * @param root
     */
    public static void inOrderRecur(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderRecur(root.left);
        System.out.print(root.val + " ");
        inOrderRecur(root.right);
    }

    /**
     * 后序遍历
     * @param root
     */
    public static void postOrderRecur(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrderRecur(root.left);
        postOrderRecur(root.right);
        System.out.print(root.val + " ");
    }


}