package ch01.codingTest.p5;

public class Solution {
    public static long solution(int N, int M, int K, int[] capacity) {
        long answer = 1;
        if (M == 1) {
            return 1;
        }

        int totalCapacity = 0;
        for (int CC : capacity) {
            totalCapacity += CC;
        }

        if (totalCapacity > N) {
            if (M == 2) {
                for (int i = 0; i <= N; i++) {
                    int temp = 1;
                    if (i > capacity[0]) continue;

                    for (int j = N; j > N - i; j--) {
                        temp *= j;
                    }

                    for (int j = 1; j <= i; j++) {
                        temp /= j;
                    }

                    answer += temp;
                }

            } else {
                for (int i = 0; i <= N; i++) {
                    int temp = 1;
                    if (i > capacity[0]) continue;

                    for (int j = N; j > N - i; j--) {
                        temp *= j;
                    }

                    for (int j = 1; j <= i; j++) {
                        temp /= j;
                    }

                    for (int j = i; j <= N; j++) {
                        if (i > capacity[1]) continue;
                        for (int k = N; k > N - j; k--) {
                            temp *= k;
                        }

                        for (int k = 1; k < j - i; k++) {
                            temp /= k;
                        }
                    }

                    answer += temp;
                }
            }


        } else {
            for (int j : capacity) {
                answer *= nCr(N, j);
                N -= j;
            }
        }


        long gam = 1;
        for (int i = K - M; i <= K; i++) {
            gam *= i;
        }
        answer *= gam;

        return answer;
    }

    public static long nCr(int n, int c) {
        int result = 1;

        if (n == 0 || n == 1 || c == 0) {
            return 1;
        }

        for (int i = n - c + 1; i <= n; i++) {
            result *= i;
        }

        for (int i = 1; i <= c; i++) {
            result /= i;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] capacity = {2, 2};
        System.out.println(solution(4, 2, 1, capacity));
        System.out.println(100800);
    }
}
