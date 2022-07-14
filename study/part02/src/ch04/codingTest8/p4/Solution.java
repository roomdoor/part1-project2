package ch04.codingTest8.p4;

public class Solution {
    public static int solution(int[][] maze) {
        int answer = 0;
        int[][] dp = new int[maze.length][maze[0].length];
        dp[0][0] = 1;
        int[] key = new int[2];

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 2) {
                    key = new int[]{i, j};
                    break;
                }
            }
        }

        cur(0, 0, maze, dp, key);

        if (dp[key[0]][key[1]] == 0) {
            return 0;
        }
        cur(key[0], key[1], maze, dp, new int[]{maze.length - 1, maze[0].length - 1});

        return dp[maze.length - 1][maze[0].length - 1] % 1007;
    }

    public static void cur(int x, int y, int[][] maze, int[][] dp, int[] key) {
        if (x > key[0] || y > key[1] || (x == key[0] && y == key[1])) {
            return;
        }

        if (isPossible(x + 1, y, maze)) {
            dp[x + 1][y] += dp[x][y];
            dp[x + 1][y] %= 1007;
            cur(x + 1, y, maze, dp, key);
        }

        if (isPossible(x, y + 1, maze)) {
            dp[x][y + 1] += dp[x][y];
            dp[x][y + 1] %= 1007;
            cur(x, y + 1, maze, dp, key);
        }
    }

    public static boolean isPossible(int x, int y, int[][] maze) {
        return x < maze.length && y < maze[0].length && (maze[x][y] == 0 || maze[x][y] == 2);
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{{0, 1, 0}, {0, 2, 0}, {1, 0, 0}};
        System.out.println(solution(a));
        a = new int[][]{{0, 1, 0, 0}, {1, 2, 0, 0}, {1, 0, 0, 0}};
//        System.out.println(solution(a));


    }
}



