package ch04.codingTest5.p5;

import java.util.Arrays;

public class Solution {
    public static int solution(int N, int M, int[] fry, int[] clean) {
        int answer = 0;
        int[] total = new int[fry.length];

        for (int i = 0; i < fry.length; i++) {
            total[i] = fry[i] + clean[i];
        }

        int max = Arrays.stream(total).max().getAsInt();

        int left = 1;
        int right = M  * max;
        int chicken = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            chicken = 0;
            for (int i = 0; i < total.length; i++) {
                chicken += (mid / total[i]);
                if ((mid % total[i]) >= fry[i]) {
                    chicken += 1;
                }
            }

            if (chicken >= M) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        answer = left;


        return answer;
    }

    public static void main(String[] args) {
        int[] a = new int[]{3, 6};
        int[] b = new int[]{2, 1};
//        System.out.println(solution(2, 20, a, b));
        System.out.println("++++++++++++++++++++++++++++++++++++");
        a = new int[]{2, 2, 1, 3};
        b = new int[]{2, 4, 3, 2};
        System.out.println(solution(4, 2, a, b));

        System.out.println(Integer.MAX_VALUE);
    }
}