class Solution:
    def maximalRectangle(self, matrix):
        if not matrix:
            return 0

        cols = len(matrix[0])
        heights = [0] * cols
        max_area = 0

        for row in matrix:
            for i in range(cols):
                if row[i] == "1":
                    heights[i] += 1
                else:
                    heights[i] = 0

            stack = []

            for i in range(cols + 1):
                current_height = 0 if i == cols else heights[i]

                while stack and current_height < heights[stack[-1]]:
                    h = heights[stack.pop()]

                    if stack:
                        width = i - stack[-1] - 1
                    else:
                        width = i

                    max_area = max(max_area, h * width)

                stack.append(i)

        return max_area