package ch04.codingTest8.p3;


import java.util.PriorityQueue;

public class Solution {
    public int solution(int N, int[] rewards) {
        int answer = 0;
        boolean[] isKilled = new boolean[rewards.length];

        PriorityQueue<int[]> queue = new PriorityQueue<>((x, y) -> y[1] - x[1]);
        for (int i = 0; i < rewards.length; i++) {
            queue.add(new int[]{i, rewards[i]});
        }

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            if (!isKilled[now[0]]) {
                for (int i = -1; i <= 1; i++) {
                    isKilled[circleNum(now[0] + i, rewards.length)] = true;
                }
                answer += rewards[now[0]];
            }
        }

        return answer;
    }

    public static int circleNum(int n, int leg) {
        if (n == -1) {
            return leg - 1;
        }

        if (n == leg) {
            return 0;
        }

        return n;

    }
}

