package ch04.codingTest10.p5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static int solution(int N, int[][] edge) {
        int answer = 0;
        List<List<int[]>> graph = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] ints : edge) {
            graph.get(ints[0]).add(ints);
        }

        int[] DP = new int[N];
        int INF = Integer.MAX_VALUE;
        Arrays.fill(DP, INF);
        DP[0] = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < graph.size(); j++) {
                List<int[]> Node = graph.get(j);
                for (int[] next : Node) {
                    if (DP[j] != INF && DP[next[1]] > DP[j] + next[2]) {
                        DP[next[1]] = DP[j] + next[2];
                    }
                }
            }
        }

        int max = 0;
        for (int i = 0; i < DP.length; i++) {
            if (DP[i] > max) {
                max = DP[i];
                answer = i;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{{0, 1, 5}, {0, 2, 7}, {1, 3, 10}, {3, 4, 8}, {2, 4, 9}, {4, 2, 1}};
        System.out.println(solution(5,a));
    }
}
