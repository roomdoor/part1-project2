package ch04.codingTest5.p4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
    public static int solution(int N, int[] branches) {
        int answer = 0;
        int count = 0;
        int min = Arrays.stream(branches).max().getAsInt();

        int left = 1;
        int right = min;

        while (left <= right) {
            int mid = (left + right) / 2;
            count = 0;
            for (int branch : branches) {
                count += branch / mid;
            }

            if (count < N) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        answer = right;
        if (right == 0) {
            return -1;
        }
        for (int branch : branches) {
            count += branch / answer;
        }

        if (count < N) {
            return -1;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] a = new int[]{6, 7, 10, 16, 12};
        System.out.println(solution(10, a));
    }
}
