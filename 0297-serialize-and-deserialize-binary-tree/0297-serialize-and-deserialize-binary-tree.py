class Codec:
    def serialize(self, root):
        """
        Preorder DFS: encode each node's value, '#' for nulls.
        Example: [1,2,3,null,null,4,5] → "1,2,#,#,3,4,#,#,5,#,#"
        """
        tokens = []

        def dfs(node):
            if node is None:
                tokens.append("#")
                return
            tokens.append(str(node.val))
            dfs(node.left)
            dfs(node.right)

        dfs(root)
        return ",".join(tokens)

    def deserialize(self, data):
        """
        Consume tokens left-to-right; '#' → return None,
        otherwise build node and recurse for left/right children.
        """
        it = iter(data.split(","))

        def dfs():
            token = next(it)
            if token == "#":
                return None
            node = TreeNode(int(token))
            node.left = dfs()
            node.right = dfs()
            return node

        return dfs()