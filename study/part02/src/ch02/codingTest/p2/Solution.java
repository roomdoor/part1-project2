package ch02.codingTest.p2;

import java.util.Arrays;
import java.util.Stack;

public class Solution {
    public static int[] solution(int[] a, int[] b) {
        int[] answer = {};

        Stack<Integer> stack = new Stack<>();
        int aLen = a.length;
        int bLen = b.length;
        if (aLen < bLen) {
            int[] temp = a.clone();
            a = b.clone();
            b = temp.clone();
        }
        aLen = a.length;
        bLen = b.length;

        boolean carry = false;
        for (int i = aLen - 1; i >= 0 || carry; i--) {
            int numA = 0;
            int numB = 0;
            int result = 0;

            if (carry) {
                if (isPossible(aLen, i)) {
                    numA = a[i];
                }

                if (isPossible(bLen, i - (aLen - bLen))) {
                    numB = b[i - (aLen - bLen)];
                }

                result = numA + numB + 1;

                carry = result >= 10;

            } else {
                if (isPossible(aLen, i)) {
                    numA = a[i];
                }
                if (isPossible(bLen, i - (aLen - bLen))) {
                    numB = b[i - (aLen - bLen)];
                }

                result = numA + numB;

                carry = result >= 10;

            }

            stack.push(result % 10);
        }

        answer = new int[stack.size()];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = stack.pop();
        }

        return answer;
    }

    public static boolean isPossible(int length, int num) {
        return num < length && num >= 0;
    }

    public static void main(String[] args) {

        int[] a = {9};
        int[] b = {1, 1};

        System.out.println(Arrays.toString(solution(a, b)));

    }
}
