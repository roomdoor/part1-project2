package ch02.codingTest.p3;

import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solution {
    public static String solution(String code) {
        String answer = "";

        char[] arr = code.toCharArray();
        answer = culString(arr, answer, 0, 1);

        return answer;
    }

    public static String culString(char[] arr, String result, int dp, int num) {
        Stack<Character> stack = new Stack<>();
        Queue<Character> queue = new LinkedList<>();

        if (!isString(arr, dp)) {                   // 글자가 아닐 때
            if (arr[dp] != '{' || arr[dp] != '}') { // 숫자 일 때
                num = arr[dp];
            } else {                                // 괄호 일 때
                if (arr[dp] == '{') {
                    stack.push('{');
                } else {
                    stack.pop();
                }
            }

            if (!stack.isEmpty()) {
                queue.add(arr[dp]);

            }



        } else {

        }


        return result;
    }

    public static boolean isString(char[] arr, int dp) {
        boolean isString = true;
        switch (arr[dp]) {
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            case '{':
            case '}':
                isString = false;
                break;
        }
        return isString;
    }

    public static void main(String[] args) {

        String a = "2{a2{b}}";
        System.out.println(solution(a));
    }
}

//hellohellohellohellohellofriend
//"5{he2{l}o}friend"
