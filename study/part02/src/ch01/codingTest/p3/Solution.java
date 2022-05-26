package ch01.codingTest.p3;


// 이 문제는 N이 1 ~ 6 정도까지 손으로 그려보다가 일정한 규칙이 있어서 간단하게 풀었습니다.
// N = 1    answer = 1
// N = 2    answer = 2
// N = 3    answer = 3
// N = 4    answer = 5
// N = 5    answer = 8
// N = 6    answer = 13
// 다음과 같은 규칙이 있어 답을 찾을수 있었습니다.


public class Solution {
    public int solution(int N) {
        int answer = 0;

        if (N == 1) {
            return 1;
        }

        if (N == 2) {
            return 2;
        }

        answer = solution(N - 1) + solution(N - 2);

        return answer;
    }

}
