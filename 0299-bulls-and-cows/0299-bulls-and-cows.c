#include <string.h>

char* getHint(char* secret, char* guess) {
    int bulls = 0, cows = 0;
    int secret_freq[10] = {0};
    int guess_freq[10] = {0};

    for (int i = 0; secret[i] != '\0'; i++) {
        int s = secret[i] - '0';
        int g = guess[i] - '0';

        if (s == g) {
            bulls++;
        } else {
            secret_freq[s]++;
            guess_freq[g]++;
        }
    }

    for (int d = 0; d < 10; d++) {
        cows += secret_freq[d] < guess_freq[d] ? secret_freq[d] : guess_freq[d];
    }

    char* result = (char*)malloc(20 * sizeof(char));
    sprintf(result, "%dA%dB", bulls, cows);
    return result;
}