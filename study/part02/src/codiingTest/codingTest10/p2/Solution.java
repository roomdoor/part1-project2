package ch04.codingTest10.p2;


import java.util.Arrays;

public class Solution {
    public static int solution(int num) {
        int answer = 0;
        String numString = String.valueOf(num);
        char[] numOrigin = numString.toCharArray();
        char[] numDP = new char[numString.length()];
        int maxNum = 0;
        int index = -1;
        for (int i = numString.length() - 1; i >= 0; i--) {
            if ((numString.charAt(i) - '0') >= maxNum) {
                index = i;
                maxNum = (numString.charAt(i) - '0');
                numDP[i] = numString.charAt(i);
            } else {
                numDP[i] = numDP[i + 1];
            }
        }

        if (Arrays.equals(numOrigin, numDP)) {
            return num;
        } else {
            int first = 0;
            int last = 0;
            for (int i = 0; i < numDP.length; i++) {
                if (numDP[i] != numOrigin[i]) {
                    first = i;
                    break;
                }
            }

            for (int i = numDP.length - 1; i >= 0; i--) {
                if (numDP[i] == numDP[first]) {
                    last = i;
                    break;
                }
            }
            char temp = numOrigin[first];
            numOrigin[first] = numOrigin[last];
            numOrigin[last] = temp;

            for (char c : numOrigin) {
                answer *= 10;
                answer += c - '0';
            }
            return answer;
        }

    }

    public static void main(String[] args) {
        System.out.println(solution(1234));
    }
}










