package leetcode;

import java.util.ArrayList;
import java.util.List;

import leetcode.TreeNode.*;

public class TreeMain {
    public static void main(String[] args) {
        Integer[] tree = {1,2,3,4,5,null,8,null,null,6,7,9};
        TreeNode root = new TreeNode(tree);
        System.out.println(root);

        TreeNode.preOrderRecur(root);
        System.out.println("pre" + preorderTraversal(root));

        TreeNode.inOrderRecur(root);
        System.out.println("in" + inorderTraversal(root));

        TreeNode.postOrderRecur(root);
        System.out.println("post" + postorderTraversal(root));

        System.out.println("level" + levelOrder(root));
    }

    /**
     * leetcode 144. 二叉树的前序遍历
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        res.add(root.val);
        res.addAll(preorderTraversal(root.left));
        res.addAll(preorderTraversal(root.right));
        return res;
    }

    /**
     * leetcode 94. 二叉树的中序遍历
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        res.addAll(inorderTraversal(root.left));
        res.add(root.val);
        res.addAll(inorderTraversal(root.right));
        return res;
    }

    /**
     * leetcode 145. 二叉树的后序遍历
     * @param root
     * @return
     */
    public static List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        res.addAll(postorderTraversal(root.left));
        res.addAll(postorderTraversal(root.right));
        res.add(root.val);
        return res;
    }

    /**
     * leetcode 102. 二叉树的层序遍历
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> res1 = new ArrayList<>();
        res1.add(1);
        res1.add(1);
        res1.add(1);
        List<Integer> res2 = new ArrayList<>();
        res2.add(1);
        res2.add(1);
        res2.add(1);
        res.add(res1);
        res.add(res2);
        return res;
    }
}
