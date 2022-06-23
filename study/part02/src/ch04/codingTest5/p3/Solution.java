package ch04.codingTest5.p3;

public class Solution {
    public int solution(int[] arr) {
        int left = 1;
        int right = arr.length - 2;


        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] > arr[mid - 1]) {
                if (arr[mid] > arr[mid + 1]) {
                    return mid;
                } else {
                    left = mid + 1;
                }
            } else if (arr[mid] > arr[mid + 1]) {
                right = mid - 1;
            }
        }
        return -1;
    }
}
