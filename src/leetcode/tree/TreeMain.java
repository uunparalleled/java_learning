package leetcode.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeMain {
    public static void main(String[] args) {
        int[] nums = {0,1,2,3,4,5};
        Integer[] tree = {1,2,3,4,5};
        TreeNode root = new TreeNode(tree);
        System.out.println(root);

        TreeNode.preOrderRecur(root);
        System.out.println("pre" + preorderTraversal(root));

        TreeNode.inOrderRecur(root);
        System.out.println("in" + inorderTraversal(root));

        TreeNode.postOrderRecur(root);
        System.out.println("post" + postorderTraversal(root));

        System.out.println("level" + levelOrder(root));

        System.out.println(depth(root));
    }

    /**
     * leetcode 543. 二叉树的直径     函数返回深度，但全局更新直径
     */
    static int ans;
    public static int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return ans;
    }
    public static int depth(TreeNode node) {
        if (node == null) {
            return 0; // 访问到空节点了，返回0
        }
        int L = depth(node.left); // 左儿子为根的子树的深度
        int R = depth(node.right); // 右儿子为根的子树的深度
        ans = Math.max(ans, L+R); // 计算d_node即L+R+1 并更新ans
        return Math.max(L, R) + 1; // 返回该节点为根的子树的深度
    }

    /**
     * leetcode 101. 对称二叉树
     */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root.left,root.right);
    }
    public static boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;

        return  left.val == right.val
                && isSymmetric(left.right, right.left)
                && isSymmetric(left.left, right.right);
    }

    /**
     * leetcode 226. 翻转二叉树
     */
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        if (root.left == null && root.right == null) return root;

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        root.left = invertTree(root.left);
        root.right = invertTree(root.right);

        return root;
    }

    /**
     * leetcode 104. 二叉树的最大深度
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left),maxDepth(root.right));
    }

    /**
     * leetcode 144. 二叉树的前序遍历
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
        if (root == null) return new ArrayList<>();

        List<List<Integer>> res = new ArrayList<>();
        List<TreeNode> tmp = new ArrayList<>();
        List<Integer> tmpRes = new ArrayList<>();
        List<TreeNode> nextLevel = new ArrayList<>();
        tmp.add(root);

        while (!tmp.isEmpty()) {
            for (TreeNode node : tmp) {
                tmpRes.add(node.val);
                if (node.left != null) nextLevel.add(node.left);
                if (node.right != null) nextLevel.add(node.right);
            }
            tmp = nextLevel;
            nextLevel = new ArrayList<>();
            res.add(tmpRes);
            tmpRes =  new ArrayList<>();
        }

        return res;
    }
}
