package ch04.codingTest10.p4;


public class Solution {
    public boolean solution(int[] param0) {
        boolean answer = true;
        int len = param0.length;
        int position = 0;

        for (int i = 0; i < len; i++) {
            if (position < i) {
                return false;
            } else if (i + param0[i] >= len) {
                return true;
            }

            position = Math.max(position, i + param0[i]);
        }

        return answer;
    }
}

