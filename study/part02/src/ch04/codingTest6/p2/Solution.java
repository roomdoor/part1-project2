package ch04.codingTest6.p2;

import java.util.Arrays;

public class Solution {
    public static int solution(int[] amount, int[] value, int[] stomach) {
        int answer = 0;
        int peopleNum = stomach.length;
        int totalNum = 0;

        int[][] set = new int[amount.length][2];
        for (int i = 0; i < amount.length; i++) {
            set[i][0] = amount[i];
            set[i][1] = value[i];
        }

        Arrays.sort(set, (x, y) -> -x[1] + y[1]);

        if (set[0][0] >= peopleNum) {

            answer += (set[0][0] / peopleNum) * set[0][1] * peopleNum;
        }
        for (int i = 0; i < peopleNum; i++) {
            stomach[i] -= (set[0][0] / peopleNum);
            totalNum += stomach[i];
        }


        for (int i = 1; i < amount.length; i++) {
            if (totalNum == 0) {
                break;
            }
            if (totalNum >= set[i][0]) {
                answer += set[i][0] * set[i][1];
                totalNum -= set[i][0];
            } else {
                answer += totalNum * set[i][1];
            }
        }


        return answer;
    }

    public static void main(String[] args) {
        int[] a = new int[]{10,1,1,1};
        int[] b = new int[]{10,1,1,1};
        int[] c = new int[]{1, 2, 2, 2};
        System.out.println(solution(a, b, c));
    }
}
