package ch01.codingTest.p4;

public class Solution {
    public static int solution(int n, int i, int j) {
        int answer = 0;
        int[][] table = new int[n][n];

        makeT(table, n, 0, n - 1, 1, i, j);
        answer = table[i][j];

        for (int k = 0; k < n; k++) {
            for (int l = 0; l < n; l++) {
                System.out.printf("%02d ",table[k][l]);
            }
            System.out.println();
        }

        return answer;
    }

    public static int makeT(int[][] table, int n, int x, int y, int nextStart, int i, int j) {
        if (x > i || x + n <= i || y < j || y - n >= j) {
            return n * n + nextStart;
        }


        if (n == 2) {
            table[x][y] = nextStart;
            table[x][y - 1] = nextStart + 1;
            table[x + 1][y - 1] = nextStart + 2;
            table[x + 1][y] = nextStart + 3;

            return nextStart + 4;
        }

        nextStart = makeT(table, n / 2, x, y, nextStart, i, j);
        nextStart = makeT(table, n / 2, x, y - n / 2, nextStart, i, j);
        nextStart = makeT(table, n / 2, x + n / 2, y - n / 2, nextStart, i, j);
        nextStart = makeT(table, n / 2, x + n / 2, y, nextStart, i, j);

        return nextStart;
    }

    public static void main(String[] args) {
        solution(8, 0, 0);
    }
}
