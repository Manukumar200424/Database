import java.util.*;

class Solution {

    private int preorderIndex = 0;
    private Map<Integer, Integer> inorderMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        // Store inorder value -> index
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return construct(preorder, 0, inorder.length - 1);
    }

    private TreeNode construct(int[] preorder, int left, int right) {

        // No elements to construct
        if (left > right) {
            return null;
        }

        // Pick current root from preorder
        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);

        // Find root position in inorder
        int inorderIndex = inorderMap.get(rootValue);

        // Build left subtree
        root.left = construct(preorder, left, inorderIndex - 1);

        // Build right subtree
        root.right = construct(preorder, inorderIndex + 1, right);

        return root;
    }
}