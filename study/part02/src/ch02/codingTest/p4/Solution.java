package ch02.codingTest.p4;

public class Solution {
    public static int solution(int delay, int capacity, int[] times) {
        int answer = 0;
        int totalTime = 0;
        int curCap = 0;

        for (int i = 0; i < times.length; i++) {
            totalTime += times[i];

            if (totalTime >= delay) {
                totalTime -= delay;
                curCap--;
            }

            if (curCap >= capacity) {
                answer++;
            } else {
                curCap++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(3, 1, new int[]{0,0,0,0,3}));
    }
}
