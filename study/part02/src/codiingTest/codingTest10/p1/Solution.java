package ch04.codingTest10.p1;

public class Solution {
    public static String[] solution(int n) {
        String[] answer = {"", "", "", "", ""};
        String num = String.valueOf(n);
        for (int i = 0; i < num.length(); i++) {
            for (int j = 1; j <= 5; j++) {
                answer[j - 1] += makeString(num.charAt(i) - '0', j);
                if (i != num.length() - 1) {
                    answer[j - 1] += " ";
                }
            }
        }

        return answer;
    }

    public static String makeString(int n, int line) {
        switch (n) {
            case 0:

                if (line == 1 || line == 5) {
                    return "#####";
                } else {
                    return "#---#";
                }

            case 1:
                return "--#--";

            case 2:
                if (line == 1 || line == 5 || line == 3) {
                    return "####";
                } else if (line == 2) {
                    return "---#";
                } else if (line == 4) {
                    return "#---";
                }

            case 3:
                if (line == 1 || line == 5 || line == 3) {
                    return "####";
                } else {
                    return "---#";
                }

            case 4:
                if (line == 3) {
                    return "#####";
                } else if (line < 3) {
                    return "#---#";
                } else {
                    return "----#";
                }

            case 5:
                if (line == 1 || line == 5 || line == 3) {
                    return "#####";
                } else if (line == 2) {
                    return "#----";
                } else {
                    return "----#";
                }

            case 6:
                if (line == 1 || line == 3 || line == 5) {
                    return "#####";
                } else if (line == 2) {
                    return "#----";
                } else {
                    return "#---#";
                }

            case 7:
                if (line == 1) {
                    return "#####";
                } else {
                    return "----#";
                }

            case 8:
                if (line == 1 || line == 5 || line == 3) {
                    return "#####";
                } else {
                    return "#---#";
                }

            case 9:
                if (line == 1 || line == 3) {
                    return "#####";
                } else if (line == 2) {
                    return "#---#";
                } else {
                    return "----#";
                }
        }

        return "??????";
    }

    public static void main(String[] args) {
        String[] a = solution(1234567890);
        for (int i = 0; i < 5; i++) {
            System.out.println(a[i] + " ");
        }
    }
}
