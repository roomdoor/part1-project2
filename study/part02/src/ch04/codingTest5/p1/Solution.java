package ch04.codingTest5.p1;


public class Solution {
    public boolean solution(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        boolean answer = true;
        int[] arr = new int[26];

        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            arr[t.charAt(i) - 'a']--;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                answer = true;
            } else {
                return false;
            }
        }
        return answer;
    }
}