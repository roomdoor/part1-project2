package ch02.codingTest.p5;

public class Solution {
    public static int[][] solution(int[][] image, int K) {
        int xLen = image.length;
        int yLen = image[0].length;
        int[][] answer = new int[xLen][yLen];
        int sum = 0;
        for (int i = 0; i < K / 2 + 1; i++) {
            for (int j = 0; j < K / 2 + 1; j++) {
                if (isPossible(i, j, xLen, yLen)) {
                    sum += image[i][j];
                }
            }
        }
        answer[0][0] = sum / (K * K);
        boolean down = false;
        boolean left = false;
        int x = 0;
        int y = 0;
        while (true) {

            if (down) {
                if (y == yLen - 1) {
                    x++;
                    left = true;
                } else if (y == 0) {
                    x++;
                    left = false;
                }
                if (x >= xLen) {
                    break;
                }

                for (int i = -K / 2; i < K / 2 + 1; i++) {
                    if (isPossible(x - K / 2 - 1, y - i, xLen, yLen)) {
                        sum -= image[x - K / 2 - 1][y - i];
                    }

                    if (isPossible(x + K / 2, y + i, xLen, yLen)) {
                        sum += image[x + K / 2][y + i];
                    }
                }

                answer[x][y] = sum / (K * K);

                down = false;
            }

            if (left) {
                y--;
                for (int i = -K / 2; i < K / 2 + 1; i++) {
                    if (isPossible(x + i, y - K / 2, xLen, yLen)) {
                        sum += image[x + i][y - K / 2];
                    }

                    if (isPossible(x + i, y + K / 2 + 1, xLen, yLen)) {
                        sum -= image[x + i][y + K / 2 + 1];
                    }
                }

                answer[x][y] = sum / (K * K);

                if (y == 0) {
                    down = true;
                }
            } else {
                y++;
                for (int i = -K / 2; i < K / 2 + 1; i++) {
                    if (isPossible(x + i, y - K / 2 - 1, xLen, yLen)) {
                        sum -= image[x + i][y - K / 2 - 1];
                    }

                    if (isPossible(x + i, y + K / 2, xLen, yLen)) {
                        sum += image[x + i][y + K / 2];
                    }
                }

                answer[x][y] = sum / (K * K);

                if (y == yLen - 1) {
                    down = true;
                }
            }


        }

        return answer;
    }

    public static boolean isPossible(int x, int y, int xLen, int yLen) {
        return x >= 0 && y >= 0 && x < xLen && y < yLen;
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{4, 5, 2, 6, 7},
                {5, 4, 2, 4, 6},
                {6, 8, 4, 8, 7},
                {7, 3, 6, 6, 4},
                {5, 0, 4, 1, 5}};

//        int[][] arr = new int[][]
//                {{1, 1, 1, 1, 1},
//                {2, 2, 2, 2, 2},
//                {3, 3, 3, 3, 3},
//                {4, 4, 4, 4, 4},
//                {5, 5, 5, 5, 5}};
        int[][] result = solution(arr, 3);
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                System.out.printf("%d ", result[i][j]);
            }
            System.out.println();
        }
    }
}
