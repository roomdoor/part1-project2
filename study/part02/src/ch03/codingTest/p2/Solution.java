package ch03.codingTest.p2;


import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
    private static class Num implements Comparable<Num> {
        int cur;
        int count;

        public Num() {
        }

        public Num(int cur, int count) {
            this.cur = cur;
            this.count = count;
        }


        @Override
        public int compareTo(Num o) {
            return this.count - o.count;
        }
    }

    public static int solution(int[] numbers, int target) {
        int answer = 0;
        int[] numCount = new int[target + 1];
        Arrays.fill(numCount, Integer.MAX_VALUE);

        answer = culMinCount(numbers, numCount, target);


        return answer;
    }

    public static int culMinCount(int[] numbers, int[] numCount, int target) {
        PriorityQueue<Num> queue = new PriorityQueue<>();
        queue.add(new Num(0, 0));
        int numLen = numbers.length;
        while (!queue.isEmpty()) {
            Num now = queue.poll();

            for (int number : numbers) {
                int nextNow = now.cur * number;
                if (nextNow == target) {
                    return now.count + 1;
                }

                if (nextNow < target) {
                    if (numCount[nextNow] > now.count + 1) {
                        numCount[nextNow] = now.count + 1;
                        queue.add(new Num(nextNow, now.count + 1));
                    }
                }
            }

            for (int number : numbers) {
                int nextNow = now.cur + number;
                if (nextNow == target) {
                    return now.count + 1;
                }

                if (nextNow < target) {
                    if (numCount[nextNow] > now.count + 1) {
                        numCount[nextNow] = now.count + 1;
                        queue.add(new Num(nextNow, now.count + 1));
                    }
                }
            }

        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1,2,1}, 10));
    }

}
