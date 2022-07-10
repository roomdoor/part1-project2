package ch04.codingTest7.p2;

import java.util.HashMap;

public class Solution {
    public int solution(int[] votes) {
        int answer = 0;
        if (votes.length <= 2) {
            return votes[0];
        }

        int before = votes[0];
        int count = 0;
        for (int i = 1; i < votes.length; i++) {
            if (votes[i] == before) {
                count++;
            } else {
                count--;
                if (count < 0) {
                    before = votes[i];
                    count = 0;
                }
            }
        }

        answer = before;

        return answer;
    }
}
