from collections import Counter

class Solution:
    def leastInterval(self, tasks, n):
        freq = Counter(tasks)

        maxFreq = max(freq.values())
        maxCount = sum(1 for f in freq.values() if f == maxFreq)

        return max(len(tasks),
                   (maxFreq - 1) * (n + 1) + maxCount)