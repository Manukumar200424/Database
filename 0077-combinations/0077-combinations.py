class Solution:
    def combine(self, n, k):

        result = []

        def backtrack(start, path):

            # If combination size becomes k
            if len(path) == k:
                result.append(path[:])
                return

            # Generate combinations
            for i in range(start, n + 1):
                path.append(i)
                backtrack(i + 1, path)
                path.pop()

        backtrack(1, [])

        return result