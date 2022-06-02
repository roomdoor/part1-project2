package ch02.codingTest.p1;

public class Solution {
    public int solution(int[] numbers) {
        int answer = 0;
        boolean[] isChecked = new boolean[100001];

        for (int number : numbers) {
            isChecked[number] = true;
        }

        boolean isBlacked = false;
        for (int i = 0; i < isChecked.length; i++) {
            if (isChecked[i]) {
                isBlacked = true;
            } else {
                if (isBlacked) {
                    answer = i;
                    return answer;
                }
            }
        }

        return answer;

    }
}
