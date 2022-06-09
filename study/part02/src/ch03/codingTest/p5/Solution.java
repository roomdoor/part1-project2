package ch03.codingTest.p5;

import java.util.PriorityQueue;

public class Solution {
    private static class Player implements Comparable<Player> {
        int attack;
        int time;
        int bossNum;

        public Player(int attack, int time, int bossNum) {
            this.attack = attack;
            this.time = time;
            this.bossNum = bossNum;
        }

        @Override
        public int compareTo(Player o) {
            return this.time - o.time;
        }
    }


    public static int solution(int[] reward, int[] health, int[] optional) {
        int answer = 0;
        answer = bfs(reward, health, optional);
        return answer;
    }

    public static int bfs(int[] reward, int[] health, int[] optional) {
        PriorityQueue<Player> queue = new PriorityQueue<>();
        queue.add(new Player(1, 0, 0));
        int finishBossNum = optional.length - 1;
        while (!queue.isEmpty()) {
            Player cur = queue.poll();


            if (cur.bossNum == finishBossNum) {
                if (optional[cur.bossNum] == 1) {
                    return cur.time;
                } else {
                    return cur.time + health[cur.bossNum] / cur.attack;
                }
            }

            int nextA = cur.attack + reward[cur.bossNum];
            int nextT = cur.time + health[cur.bossNum] / cur.attack + (health[cur.bossNum] % cur.attack == 0 ? 0 : 1);

            if (optional[cur.bossNum] == 1) { // 선택
                queue.add(new Player(nextA, nextT, cur.bossNum + 1));
                queue.add(new Player(cur.attack, cur.time, cur.bossNum + 1));
            } else {                        // 필수
                queue.add(new Player(nextA, nextT, cur.bossNum + 1));
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        int[] reward = {4, 2, 2, 0, 3, 5};
        int[] health = {10, 20, 20, 20, 40, 30};
        int[] optional = {1, 0, 1, 0, 0, 0};

        System.out.println(solution(reward, health, optional));

    }
}
//            System.out.println("================================");
//            String b = optional[cur.bossNum] == 1 ? "선택" : "필수";
//            System.out.println("보스 번호 = " + cur.bossNum + " 필수/선택 = " + b);
//            System.out.println("현재 시간 = " + cur.time);
//            System.out.println("================================");