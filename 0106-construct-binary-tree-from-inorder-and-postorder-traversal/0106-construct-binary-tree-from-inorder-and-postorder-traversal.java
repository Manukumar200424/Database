import java.util.HashMap;
import java.util.Map;

class Solution {

    int postIndex;
    Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {

        // Start from last index of postorder
        postIndex = postorder.length - 1;

        // Store inorder value -> index
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return helper(postorder, 0, inorder.length - 1);
    }

    public TreeNode helper(int[] postorder, int left, int right) {

        // Base condition
        if (left > right) {
            return null;
        }

        // Root value from postorder
        int rootValue = postorder[postIndex--];

        // Create root node
        TreeNode root = new TreeNode(rootValue);

        // Find root index in inorder
        int index = map.get(rootValue);

        // Build RIGHT subtree first
        root.right = helper(postorder, index + 1, right);

        // Build LEFT subtree
        root.left = helper(postorder, left, index - 1);

        return root;
    }
}