class Solution(object):
    def findLadders(self, beginWord, endWord, wordList):

        wordSet = set(wordList)

        if endWord not in wordSet:
            return []

        parents = defaultdict(list)
        level = {beginWord}
        found = False

        while level and not found:
            next_level = defaultdict(list)

            for word in level:
                wordSet.discard(word)

            for word in level:
                for i in range(len(word)):
                    for c in "abcdefghijklmnopqrstuvwxyz":
                        new_word = word[:i] + c + word[i + 1:]

                        if new_word in wordSet:
                            next_level[new_word].append(word)

                            if new_word == endWord:
                                found = True

            level = set(next_level.keys())

            for word in next_level:
                parents[word].extend(next_level[word])

        if not found:
            return []

        res = []

        def dfs(word, path):
            if word == beginWord:
                res.append(path[::-1])
                return

            for parent in parents[word]:
                dfs(parent, path + [parent])

        dfs(endWord, [endWord])

        return res