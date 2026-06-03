# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution(object):
    def flatten(self, root):
        curr = root

        while curr:
            if curr.left:
                prev = curr.left

                while prev.right:
                    prev = prev.right

                prev.right = curr.right
                curr.right = curr.left
                curr.left = None

            curr = curr.right
        