package ch04.codingTest5.p2;

import java.util.Arrays;

public class Solution {
    public static String solution(int[] numbers) {
        StringBuilder answer = new StringBuilder();
        String[] arr = new String[numbers.length];
        String[] tmp = new String[numbers.length];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = String.valueOf(numbers[i]);
        }

        mergeSort(arr, tmp, 0, numbers.length - 1);
        System.out.println(Arrays.toString(tmp));

        for (String s : tmp) {
            answer.append(s);
        }

        return answer.toString();
    }

    public static void mergeSort(String[] arr, String[] tmp, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, tmp, left, mid);
            mergeSort(arr, tmp, mid + 1, right);
            merge(arr, tmp, left, right, mid);
        }
    }

    public static void merge(String[] arr, String[] tmp, int left, int right, int mid) {
        int p = left;
        int q = mid + 1;
        int idx = p;

        while (p <= mid || q <= right) {
            if (p <= mid && q <= right) {
                String a = arr[p] + arr[q];
                String b = arr[q] + arr[p];
                if (a.compareTo(b) > 0) {
                    tmp[idx++] = arr[p++];
                } else {
                    tmp[idx++] = arr[q++];
                }
            } else if (p <= mid) {
                tmp[idx++] = arr[p++];
            } else {
                tmp[idx++] = arr[q++];
            }
        }

        if (right + 1 - left >= 0) System.arraycopy(tmp, left, arr, left, right + 1 - left);
    }

    public static void main(String[] args) {
        int[] a = new int[]{9, 99, 98, 1, 12};
        System.out.println(solution(a));
//        String q = "1";
//        String w = "2";
//        String qw = q + w;
//        String wq = w + q;
//        System.out.println(qw + "  " + wq);
//        System.out.println(qw.compareTo(wq));

    }
}

