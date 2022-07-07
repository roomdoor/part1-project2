package ch04.Algorithm_13;// Practice
// 정수 n 이 주어졌을 때 아래의 연산을 통해 1로 만들려고 한다.
// 2로 나누어 떨어지면 2로 나누기
// 3으로 나누어 떨어지면 3으로 나누기
// 1 빼기
// 위의 연산을 통해 1로 만들 때 필요한 최소한의 연산 횟수를 출력하는 프로그램을 작성하세요.

// 입출력 예시
// n: 2
// 출력: 1

// n: 9
// 출력: 2

public class Practice1 {
    public static int solution(int n) {
        int[] dp = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;

            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i / 2] + 1, dp[i]);
            }

            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i / 3] + 1, dp[i]);
            }
        }

        return dp[n];
    }

    public static int solution1(int n, int[] dp) {
        if (n == 1) {
            return 0;
        }

        if (n <= 3) {
            return 1;
        }

        if (dp[n] != 0) {
            return dp[n];
        }

        dp[n] = solution1(n - 1, dp) + 1;

        if (n % 2 == 0) {
            dp[n] = Math.min(dp[n], solution1(n / 2, dp) + 1);
        }

        if (n % 3 == 0) {
            dp[n] = Math.min(dp[n], solution1(n / 3, dp) + 1);
        }

        return dp[n];
    }

    public static void main(String[] args) {
        // Test code
        System.out.println(solution(2));    // 1
        System.out.println(solution(4));    // 2
        System.out.println(solution(9));    // 2
        System.out.println(solution(10));   // 3


        System.out.println("-------------------------");
        int n = 2;
        int[] dp;
        System.out.println(solution1(n, dp = new int[n + 1]));
        n = 4;
        System.out.println(solution1(n, dp = new int[n + 1]));
        n = 9;
        System.out.println(solution1(n, dp = new int[n + 1]));
        n = 10;
        System.out.println(solution1(n, dp = new int[n + 1]));
    }
}
