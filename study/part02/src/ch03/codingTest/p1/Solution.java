package ch03.codingTest.p1;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private static class Node {
        int num;
        List<Integer> trustList = new ArrayList<>();

        public Node() {
            this.trustList = new ArrayList<>();
        }

        public Node(int num, List<Integer> trustList) {
            this.num = num;
            this.trustList = trustList;
        }

        public void add(Node p) {
            this.trustList.add(p.num);
        }

    }

    public static int solution(int N, int[][] trust) {
        int answer = -1;
        Node[] listP = new Node[N + 1];

        for (int i = 1; i < N + 1; i++) {
            listP[i] = new Node(i, new ArrayList<>());
        }

        for (int[] ints : trust) {
            listP[ints[0]].trustList.add(ints[1]);
        }

        for (int i = 1; i < N + 1; i++) {
            if (listP[i].trustList.size() == 0) {
                answer = listP[i].num;
                break;
            }
        }


        return answer;
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{1, 3}, {2, 3}};
        ;
        System.out.println(solution(3, arr));
    }
}
