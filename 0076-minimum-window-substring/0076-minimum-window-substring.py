from collections import Counter

class Solution:
    def minWindow(self, s, t):

        if not s or not t:
            return ""

        need = Counter(t)
        window = {}

        have = 0
        required = len(need)

        left = 0
        min_len = float('inf')
        result = [-1, -1]

        for right in range(len(s)):

            char = s[right]
            window[char] = window.get(char, 0) + 1

            # Check if current character count matches
            if char in need and window[char] == need[char]:
                have += 1

            # Try to shrink window
            while have == required:

                # Update minimum window
                if (right - left + 1) < min_len:
                    min_len = right - left + 1
                    result = [left, right]

                # Remove left character
                window[s[left]] -= 1

                if s[left] in need and window[s[left]] < need[s[left]]:
                    have -= 1

                left += 1

        l, r = result

        return s[l:r + 1] if min_len != float('inf') else ""