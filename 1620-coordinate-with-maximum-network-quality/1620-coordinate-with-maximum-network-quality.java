import java.util.*;

class Solution {
    public int[] bestCoordinate(int[][] towers, int radius) {

        int maxQuality = -1;
        int[] answer = new int[2];

        for (int x = 0; x <= 50; x++) {
            for (int y = 0; y <= 50; y++) {

                int quality = 0;

                for (int[] tower : towers) {
                    int tx = tower[0];
                    int ty = tower[1];
                    int q = tower[2];

                    double distance =
                            Math.sqrt((x - tx) * (x - tx)
                                    + (y - ty) * (y - ty));

                    if (distance <= radius) {
                        quality += (int)(q / (1 + distance));
                    }
                }

                if (quality > maxQuality) {
                    maxQuality = quality;
                    answer[0] = x;
                    answer[1] = y;
                }
            }
        }

        return answer;
    }
}