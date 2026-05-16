class Solution:
    def fullJustify(self, words, maxWidth):
        result = []
        i = 0

        while i < len(words):
            line_words = []
            line_length = 0

            # Pack as many words as possible
            while i < len(words) and line_length + len(words[i]) + len(line_words) <= maxWidth:
                line_words.append(words[i])
                line_length += len(words[i])
                i += 1

            # Number of gaps between words
            gaps = len(line_words) - 1

            # Last line or single word -> left justify
            if i == len(words) or gaps == 0:
                line = " ".join(line_words)
                line += " " * (maxWidth - len(line))

            else:
                total_spaces = maxWidth - line_length
                even_spaces = total_spaces // gaps
                extra_spaces = total_spaces % gaps

                line = ""

                for j in range(gaps):
                    line += line_words[j]

                    # Left gaps get extra spaces
                    spaces = even_spaces
                    if j < extra_spaces:
                        spaces += 1

                    line += " " * spaces

                line += line_words[-1]

            result.append(line)

        return result