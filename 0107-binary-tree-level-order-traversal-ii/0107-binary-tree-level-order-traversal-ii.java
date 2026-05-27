import java.util.*;

class Solution {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        List<List<Integer>> result = new LinkedList<>();

        // Empty tree
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            int size = queue.size();
            List<Integer> level = new ArrayList<>();

            // Process current level
            for (int i = 0; i < size; i++) {

                TreeNode current = queue.poll();

                level.add(current.val);

                // Add left child
                if (current.left != null) {
                    queue.offer(current.left);
                }

                // Add right child
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            // Add at beginning for bottom-up order
            result.add(0, level);
        }

        return result;
    }
}