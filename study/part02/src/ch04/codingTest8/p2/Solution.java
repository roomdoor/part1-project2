package ch04.codingTest8.p2;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {

    public static int solution(int depth, int n, int[][] blocks) {
        int answer = 0;
        int INF = Integer.MAX_VALUE;
        int[][] dp = new int[blocks.length][blocks[0].length];

        for (int i = 0; i < blocks.length; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[depth][n] = 0;
        cur(depth, n, blocks, dp);
        answer = Arrays.stream(dp[0]).min().getAsInt() + blocks[depth][n];


        return answer;
    }

    public static void cur(int depth, int n, int[][] blocks, int[][] dp) {
        if (depth == 0) {
            return;
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((x, y) -> x[1] - y[1]);
        for (int i = -1; i <= 1; i++) {
            if (isPossible(n - i, dp.length, dp[0].length)) {
                queue.add(new int[]{n - i, blocks[depth - 1][n - i]});
            }
        }

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            if (dp[depth - 1][now[0]] > dp[depth][n] + blocks[depth - 1][now[0]]) {
                dp[depth - 1][now[0]] = dp[depth][n] + blocks[depth - 1][now[0]];
                cur(depth - 1, now[0], blocks, dp);
            }
        }
    }

    public static boolean isPossible(int n, int len, int len2) {
        return n < len2 && n >= 0;
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{{5, 6, 2, 6}, {1, 6, 4, 9}, {5, 6, 9, 4}, {55, 14, 21, 14}};
        System.out.println(solution(3, 3, a));

    }
}
