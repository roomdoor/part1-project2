package ch03.codingTest.p5;

import java.util.PriorityQueue;

public class Solution {
    private static class Player implements Comparable<Player> { // 플레이어의 정보를 기록할 class
        int attack;         // 공격력
        int time;           // 현재 단계까직 걸린 시간
        int bossNum;        // 다음 공략해야할 보스의 번호

        public Player(int attack, int time, int bossNum) {  // 생성자
            this.attack = attack;
            this.time = time;
            this.bossNum = bossNum;
        }

        @Override
        public int compareTo(Player o) {                    // PriorityQueue 를 위한 Comparable Override 메소드 구현

            if (o.bossNum == this.bossNum) {
                return this.time - o.time;
            } else {
                return o.bossNum - this.bossNum;
            }
        }
    }


    public static int solution(int[] reward, int[] health, int[] optional) { // solution 함수
        return bfs(reward, health, optional);
    }

    public static int bfs(int[] reward, int[] health, int[] optional) { // PriorityQueue 를 사용한 bfs
        PriorityQueue<Player> queue = new PriorityQueue<>();
        queue.add(new Player(1, 0, 0));            // 시작 상태를 queue 에 넣어줌 (공격력 1, 시간 0, 잡을 보스 번호 0
        int finishBossNum = optional.length - 1;                         // 마지막 보스 idx 를 변수로 만듬
        while (!queue.isEmpty()) {                                      // bfs 반복 시작
            Player cur = queue.poll();                                  // queue 에서 하니싹 빼서 확인

            if (cur.bossNum == finishBossNum) {                          // 이번에 잡을 보스 번호가 마지막 보스 일 때
                if (optional[cur.bossNum] == 1) {                       // 선택 보스면 스킵하고 현재 까지 걸리 시간 출력
                    return cur.time;
                } else {                                                // 아니라면 지금 공격력으로 현재 보스 잡는 시간 더해서 출력
                    return cur.time + (health[cur.bossNum] + cur.attack - 1) / cur.attack;
                    // 보스 잡는 시간 계산 = 보스 체력 / 현재 공격력 + (나누고 남은 나머지가 있으면 한대가 더 필요하므로 나머지 있을시 + 1)
                }
            }

            int nextA = cur.attack + reward[cur.bossNum];               // 현재 공격력 + 이 보스를 잡았을 때 보상
            int nextT = cur.time + (health[cur.bossNum] + cur.attack - 1) / cur.attack;
            // 현재 시간 + 이 보스를 잡았을 떄 드는 시간
            if (optional[cur.bossNum] == 1) {                           // 선택형 보스 일떄
                queue.add(new Player(nextA, nextT, cur.bossNum + 1));           // 선택형 잡았을 때 queue 에 추가
                queue.add(new Player(cur.attack, cur.time, cur.bossNum + 1));   // 선택형 스킵 했을 때 queue 에 추가
            } else {                                                    // 필수 일 때
                queue.add(new Player(nextA, nextT, cur.bossNum + 1));           // 필수 보스 잡은거 queue 에 추가
            }

        }
        return -1;                                                                       // 오류 확인용 return
    }

    public static void main(String[] args) {
        int[] reward;
        int[] health;
        int[] optional;

        System.out.println("=============================================================");
        reward = new int[]{74, 24, 69, 45, 59, 6, 84, 64, 27, 77};
        health = new int[]{28, 36, 62, 88, 100, 27, 42, 56, 9, 32};
        optional = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        System.out.println(solution(reward, health, optional));


        System.out.println("=============================================================");
        reward = new int[]{38, 27, 14, 83, 79, 87, 42, 85, 34, 50, 100, 48, 29, 54, 9, 51, 95, 85, 53, 0, 59, 27, 68, 12, 44, 2, 47, 90, 46, 1};
        health = new int[]{33, 72, 19, 4, 55, 23, 35, 8, 97, 19, 51, 87, 93, 46, 83, 72, 60, 21, 63, 65, 39, 23, 88, 67, 87, 0, 10, 8, 4, 73};
        optional = new int[]{0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0};

        System.out.println(solution(reward, health, optional));


        System.out.println("=============================================================");
        reward = new int[]{26, 54, 14, 67, 40, 31, 12, 35, 83, 22, 1, 1, 81, 50, 78, 58, 83, 72, 5, 43, 30, 34, 60, 84, 25, 70, 75, 68, 40, 42, 2, 17, 36, 44, 86, 46, 51, 51, 81, 6};
        health = new int[]{95, 28, 66, 8, 27, 18, 85, 15, 18, 43, 15, 47, 3, 57, 85, 25, 86, 85, 87, 46, 65, 84, 80, 70, 26, 16, 58, 69, 71, 40, 38, 86, 38, 94, 32, 30, 55, 26, 58, 38};
        optional = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0};

        System.out.println(solution(reward, health, optional));


        System.out.println("=============================================================");
        reward = new int[]{83, 20, 22, 93, 75, 100, 85, 94, 24, 57, 17, 4, 24, 66, 91, 87, 65, 29, 13, 75, 67, 1, 42, 65, 16, 73, 84, 61, 16, 14, 14, 5, 57, 78, 73, 91, 78, 77, 11, 35, 51, 96, 17, 89, 7, 14, 36, 87, 67, 7};
        health = new int[]{12, 38, 36, 30, 21, 92, 49, 83, 71, 48, 19, 90, 96, 90, 81, 42, 89, 4, 30, 39, 49, 30, 60, 31, 25, 52, 19, 76, 21, 63, 56, 62, 24, 35, 96, 3, 96, 24, 42, 99, 4, 28, 39, 67, 33, 4, 96, 48, 16, 32};
        optional = new int[]{0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        System.out.println(solution(reward, health, optional));


        System.out.println("=============================================================");
        reward = new int[]{61, 31, 98, 17, 98, 56, 95, 8, 49, 12, 79, 97, 61, 12, 69, 7, 86, 58, 34, 44, 48, 21, 97, 9, 71, 100, 29, 96, 91, 68, 65, 98, 53, 94, 53, 97, 22, 88, 6, 47, 94, 74, 13, 1, 21, 32, 91, 20, 67, 16, 71, 92, 36, 67, 72, 74, 86, 31, 70, 72, 93, 88, 11, 24, 17, 6, 71, 24, 60, 15, 77, 18, 61, 63, 39, 23, 17, 23, 20, 63, 54, 61, 7, 21, 45, 9, 72, 32, 22, 85, 0, 0, 41, 4, 19, 34, 29, 81, 20, 92};
        health = new int[]{52, 31, 81, 25, 41, 18, 98, 74, 42, 44, 89, 49, 40, 28, 73, 38, 37, 36, 66, 54, 20, 84, 18, 91, 1, 48, 13, 67, 55, 99, 23, 32, 27, 57, 63, 73, 26, 16, 13, 10, 53, 87, 50, 99, 1, 38, 22, 79, 16, 64, 76, 95, 51, 7, 90, 78, 45, 78, 10, 87, 22, 26, 100, 71, 86, 39, 100, 0, 22, 6, 49, 46, 91, 64, 25, 82, 89, 67, 61, 89, 19, 29, 46, 22, 1, 63, 59, 97, 81, 85, 30, 39, 56, 41, 8, 53, 85, 56, 45, 89};
        optional = new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        System.out.println(solution(reward, health, optional));


        System.out.println("=============================================================");
        reward = new int[]{83, 20, 22, 93, 75, 100, 85, 94, 24, 57, 17, 4, 24, 66, 91, 87, 65, 29, 13, 75, 67, 1, 42, 65, 16, 73, 84, 61, 16, 14, 14, 5, 57, 78, 73, 91, 78, 77, 11, 35, 51, 96, 17, 89, 7, 14, 36, 87, 67, 7};
        health = new int[]{12, 38, 36, 30, 21, 92, 49, 83, 71, 48, 19, 90, 96, 90, 81, 42, 89, 4, 30, 39, 49, 30, 60, 31, 25, 52, 19, 76, 21, 63, 56, 62, 24, 35, 96, 3, 96, 24, 42, 99, 4, 28, 39, 67, 33, 4, 96, 48, 16, 32};
        optional = new int[]{0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        System.out.println(solution(reward, health, optional));


        System.out.println("=============================================================");
        System.out.println("=============================================================");
        System.out.println("=============================================================");
        reward = new int[]{74, 24, 69, 45, 59, 6, 84, 64, 27, 77, 28, 36, 62, 88, 100, 27, 42, 56, 9, 32, 24, 20, 11, 56, 98, 68, 46, 24, 60, 100, 41, 30, 81, 51, 54, 26, 8, 55, 59, 38, 27, 14, 83, 79, 87, 42, 85, 34, 50, 100, 48, 29, 54, 9, 51, 95, 85, 53, 0, 59, 27, 68, 12, 44, 2, 47, 90, 46, 1, 33, 72, 19, 4, 55, 23, 35, 8, 97, 19, 51, 87, 93, 46, 83, 72, 60, 21, 63, 65, 39, 23, 88, 67, 87, 0, 10, 8, 4, 73, 44};
        health = new int[]{68, 89, 56, 15, 86, 90, 78, 99, 77, 58, 81, 17, 87, 22, 25, 94, 70, 57, 17, 10, 19, 39, 43, 48, 8, 7, 70, 70, 88, 44, 59, 7, 24, 35, 86, 41, 11, 35, 19, 32, 59, 26, 54, 14, 67, 40, 31, 12, 35, 83, 22, 1, 1, 81, 50, 78, 58, 83, 72, 5, 43, 30, 34, 60, 84, 25, 70, 75, 68, 40, 42, 2, 17, 36, 44, 86, 46, 51, 51, 81, 6, 95, 28, 66, 8, 27, 18, 85, 15, 18, 43, 15, 47, 3, 57, 85, 25, 86, 85, 87};
        optional = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        System.out.println(solution(reward, health, optional));

        System.out.println("=============================================================");
        reward = new int[]{30, 60, 31, 25, 52, 19, 76, 21, 63, 56, 62, 24, 35, 96, 3, 96, 24, 42, 99, 4, 28, 39, 67, 33, 4, 96, 48, 16, 32, 75, 84, 92, 82, 87, 78, 47, 99, 58, 78, 33, 7, 58, 85, 38, 11, 22, 84, 2, 47, 61, 5, 49, 5, 45, 7, 29, 0, 48, 16, 62, 50, 13, 93, 23, 53, 88, 84, 95, 93, 56, 34, 47, 93, 27, 87, 76, 36, 50, 17, 97, 21, 66, 84, 62, 38, 29, 53, 67, 5, 6, 67, 13, 91, 90, 1, 96, 89, 62, 36, 58, 80, 47, 79, 36, 17, 46, 61, 31, 98, 17, 98, 56, 95, 8, 49, 12, 79, 97, 61, 12, 69, 7, 86, 58, 34, 44, 48, 21, 97, 9, 71, 100, 29, 96, 91, 68, 65, 98, 53, 94, 53, 97, 22, 88, 6, 47, 94, 74, 13, 1, 21, 32, 91, 20, 67, 16, 71, 92, 36, 67, 72, 74, 86, 31, 70, 72, 93, 88, 11, 24, 17, 6, 71, 24, 60, 15, 77, 18, 61, 63, 39, 23, 17, 23, 20, 63, 54, 61, 7, 21, 45, 9, 72, 32, 22, 85, 0, 0, 41, 4, 19, 34, 29, 81, 20, 92, 52, 31, 81, 25, 41, 18, 98, 74, 42, 44, 89, 49, 40, 28, 73, 38, 37, 36, 66, 54, 20, 84, 18, 91, 1, 48, 13, 67, 55, 99, 23, 32, 27, 57, 63, 73, 26, 16, 13, 10, 53, 87, 50, 99, 1, 38, 22, 79, 16, 64, 76, 95, 51, 7, 90, 78, 45, 78, 10, 87, 22, 26, 100, 71, 86, 39, 100, 0, 22, 6, 49, 46, 91, 64, 25, 82, 89, 67, 61, 89, 19, 29, 46, 22, 1, 63, 59, 97, 81, 85, 30, 39, 56, 41, 8, 53, 85, 56, 45, 89, 4, 89, 81, 44, 62, 5, 9, 46, 44, 73, 41, 8, 7, 10, 59, 48, 38, 28, 35, 27, 22, 68, 25, 17, 66, 65, 36, 34, 100, 74, 13, 90, 18, 45, 88, 78, 3, 23, 78, 53, 85, 40, 76, 36, 47, 72, 43, 95, 18, 53, 85, 65, 66, 100, 28, 55, 7, 3, 39, 47, 25, 30, 97, 42, 100, 21, 9, 87, 39, 10, 39, 68, 34, 19, 44, 2, 93, 28, 30, 89, 4, 67, 0, 36, 80, 74, 51, 10, 58, 43, 97, 12, 51, 48, 49, 91, 42, 93, 53, 8, 73, 13, 33, 39, 70, 60, 98, 92, 9, 53, 25, 19, 2, 28, 62, 20, 14, 10, 66, 20, 67, 75, 36, 24, 95, 48, 59, 9, 20, 36, 42, 87, 20, 11, 96, 25, 87, 82, 78, 95, 91, 35, 5, 92, 90, 23, 44, 77, 93, 83, 7, 51, 4, 85, 21, 53, 84, 67, 92, 5, 69, 26, 16, 24, 61, 10, 93, 49, 19, 54, 22, 48, 67, 78, 42, 64, 10, 97, 12, 65, 3, 52, 19, 68, 83, 58, 85, 85, 84, 20, 86, 95, 32, 67};
        health = new int[]{72, 70, 58, 90, 15, 23, 13, 85, 57, 23, 25, 89, 28, 7, 66, 92, 81, 77, 91, 0, 16, 39, 26, 42, 30, 61, 10, 15, 6, 80, 27, 78, 96, 81, 31, 63, 71, 57, 14, 89, 46, 98, 8, 31, 40, 37, 22, 6, 77, 100, 56, 23, 35, 76, 9, 1, 4, 15, 91, 8, 9, 17, 80, 91, 90, 79, 100, 50, 36, 81, 6, 80, 98, 7, 91, 16, 83, 71, 14, 63, 55, 82, 34, 98, 14, 99, 62, 42, 97, 19, 91, 43, 56, 73, 77, 92, 30, 41, 72, 18, 23, 80, 56, 54, 94, 32, 84, 4, 70, 80, 29, 25, 100, 4, 23, 57, 61, 41, 71, 69, 37, 14, 58, 35, 45, 35, 32, 53, 31, 52, 43, 26, 76, 87, 43, 2, 67, 23, 40, 29, 13, 52, 96, 76, 67, 88, 99, 75, 64, 63, 22, 82, 42, 32, 30, 98, 67, 4, 2, 89, 46, 27, 54, 66, 91, 13, 32, 22, 59, 1, 93, 35, 17, 85, 68, 50, 72, 87, 99, 36, 68, 8, 70, 13, 36, 91, 17, 73, 70, 92, 0, 23, 63, 10, 31, 2, 46, 33, 98, 33, 100, 88, 63, 8, 28, 62, 100, 59, 80, 52, 19, 25, 35, 69, 74, 52, 81, 71, 81, 89, 79, 81, 43, 88, 22, 52, 62, 86, 14, 31, 46, 48, 65, 59, 12, 3, 35, 55, 61, 40, 47, 89, 77, 74, 62, 91, 22, 100, 41, 16, 90, 85, 75, 97, 15, 39, 19, 78, 71, 71, 83, 70, 69, 53, 95, 97, 60, 32, 39, 66, 15, 69, 5, 60, 7, 29, 47, 41, 79, 93, 28, 96, 58, 32, 34, 41, 57, 47, 90, 69, 59, 7, 66, 46, 68, 72, 48, 87, 19, 54, 36, 46, 21, 74, 65, 82, 50, 63, 40, 61, 65, 96, 96, 78, 91, 93, 67, 51, 10, 90, 14, 0, 9, 12, 28, 61, 28, 41, 100, 25, 7, 0, 95, 100, 28, 7, 31, 80, 35, 63, 32, 75, 32, 56, 9, 46, 4, 17, 73, 68, 99, 5, 63, 18, 81, 8, 52, 61, 51, 88, 97, 17, 79, 67, 5, 95, 26, 78, 21, 14, 89, 20, 69, 39, 64, 70, 25, 100, 37, 53, 81, 29, 22, 8, 52, 28, 78, 63, 77, 80, 19, 61, 58, 47, 40, 99, 38, 70, 21, 82, 93, 35, 44, 34, 80, 62, 66, 33, 64, 77, 25, 96, 68, 61, 24, 53, 6, 52, 80, 9, 57, 10, 1, 47, 96, 80, 57, 42, 51, 28, 35, 3, 36, 29, 2, 78, 43, 25, 27, 63, 76, 94, 73, 80, 28, 65, 75, 8, 37, 64, 45, 61, 92, 96, 24, 84, 78, 20, 22, 16, 6, 46, 5, 64, 45, 41, 46, 15, 12, 18, 46, 81, 74, 16, 28, 65, 92, 23, 52, 55, 1, 87, 81, 14, 61, 2, 36, 45, 95, 48, 66, 67, 72, 78, 54, 57, 26, 58, 89, 57};
        optional = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        System.out.println(solution(reward, health, optional));

        System.out.println("=============================================================");
        reward = new int[]{80, 4, 74, 47, 45, 62, 24, 37, 55, 42, 83, 26, 95, 29, 56, 97, 26, 56, 2, 31, 82, 33, 13, 44, 55, 18, 50, 56, 20, 18, 99, 48, 10, 51, 33, 28, 25, 34, 31, 4, 18, 10, 51, 16, 19, 56, 73, 59, 35, 6, 54, 10, 51, 45, 2, 66, 61, 61, 91, 97, 36, 48, 22, 55, 89, 71, 89, 19, 8, 25, 5, 2, 97, 20, 69, 13, 98, 96, 83, 85, 60, 56, 14, 17, 38, 59, 30, 12, 16, 94, 83, 29, 60, 50, 56, 44, 98, 19, 88, 86, 52, 41, 68, 24, 79, 92, 53, 69, 100, 61, 48, 45, 90, 54, 85, 9, 1, 27, 22, 86, 29, 64, 62, 18, 52, 45, 10, 100, 15, 0, 7, 56, 71, 62, 45, 72, 19, 14, 17, 14, 99, 55, 80, 98, 70, 54, 93, 81, 51, 88, 0, 69, 59, 32, 7, 17, 16, 53, 34, 73, 53, 87, 81, 82, 43, 38, 48, 4, 20, 56, 54, 55, 45, 81, 78, 82, 63, 54, 86, 51, 69, 25, 46, 64, 18, 3, 34, 77, 11, 43, 73, 21, 12, 24, 44, 37, 46, 70, 26, 90, 91, 33, 2, 88, 13, 85, 82, 76, 98, 41, 99, 15, 48, 77, 48, 30, 99, 11, 43, 3, 91, 86, 9, 93, 99, 88, 36, 18, 76, 8, 69, 75, 75, 25, 29, 33, 76, 83, 13, 3, 84, 31, 10, 31, 85, 8, 74, 60, 15, 17, 27, 48, 54, 70, 22, 60, 26, 2, 56, 45, 16, 10, 83, 58, 4, 57, 27, 44, 46, 70, 89, 23, 15, 10, 54, 43, 19, 42, 31, 32, 37, 1, 30, 27, 69, 99, 27, 11, 72, 85, 26, 63, 6, 90, 86, 49, 49, 72, 95, 31, 16, 98, 30, 6, 40, 9, 21, 82, 13, 83, 87, 41, 84, 95, 62, 97, 67, 40, 63, 96, 7, 95, 46, 100, 100, 3, 50, 51, 54, 19, 55, 96, 5, 75, 10, 51, 81, 53, 42, 36, 53, 82, 40, 6, 91, 88, 2, 55, 43, 42, 0, 5, 36, 18, 26, 37, 27, 94, 18, 48, 24, 77, 47, 6, 87, 92, 46, 62, 17, 85, 30, 35, 100, 59, 39, 86, 39, 56, 37, 2, 19, 71, 99, 14, 23, 92, 59, 66, 57, 43, 34, 73, 99, 71, 5, 36, 28, 49, 58, 49, 54, 65, 29, 19, 92, 77, 22, 70, 40, 98, 2, 85, 42, 77, 39, 34, 94, 17, 49, 18, 35, 91, 34, 99, 60, 94, 39, 51, 42, 28, 60, 56, 33, 89, 69, 5, 62, 59, 71, 1, 24, 31, 46, 42, 2, 29, 32, 7, 9, 77, 22, 90, 5, 30, 22, 81, 95, 88, 49, 51, 38, 68, 51, 70, 38, 54, 77, 49, 8, 50, 3, 29, 90, 60, 4, 72, 93, 58, 34, 7, 94, 86, 27, 7, 29, 30, 60, 52, 81, 84, 49, 57, 85, 66, 33, 73, 22, 87, 13, 29};
        health = new int[]{96, 43, 4, 84, 45, 42, 68, 44, 73, 21, 69, 60, 39, 28, 62, 71, 55, 75, 96, 94, 36, 55, 68, 40, 91, 10, 6, 9, 14, 15, 60, 15, 87, 53, 72, 82, 58, 86, 26, 42, 95, 51, 53, 90, 20, 28, 44, 5, 29, 7, 89, 13, 92, 50, 64, 53, 71, 55, 6, 81, 34, 26, 67, 1, 28, 62, 71, 90, 52, 4, 60, 52, 47, 81, 45, 81, 69, 55, 89, 81, 92, 53, 18, 97, 48, 14, 60, 99, 77, 55, 63, 98, 90, 49, 20, 38, 20, 78, 32, 36, 67, 18, 24, 18, 5, 92, 3, 90, 40, 31, 30, 88, 34, 7, 16, 41, 54, 4, 69, 31, 9, 33, 25, 86, 3, 25, 20, 56, 99, 9, 55, 81, 4, 1, 44, 48, 10, 44, 1, 69, 99, 77, 38, 34, 84, 39, 79, 34, 43, 76, 58, 64, 88, 36, 24, 99, 71, 88, 22, 75, 74, 11, 27, 76, 62, 51, 87, 44, 38, 52, 84, 74, 75, 22, 29, 68, 30, 29, 92, 14, 15, 82, 11, 72, 82, 37, 73, 10, 9, 6, 93, 3, 77, 49, 18, 88, 68, 13, 43, 72, 49, 92, 30, 41, 52, 5, 33, 92, 77, 92, 38, 81, 93, 94, 3, 66, 74, 80, 57, 90, 52, 57, 7, 61, 53, 30, 46, 10, 2, 46, 1, 55, 64, 82, 77, 70, 93, 65, 13, 39, 48, 14, 48, 23, 96, 71, 78, 11, 87, 25, 28, 63, 14, 63, 72, 31, 15, 15, 45, 98, 61, 6, 100, 94, 10, 2, 94, 29, 92, 89, 35, 8, 29, 65, 48, 82, 97, 80, 2, 12, 23, 83, 48, 5, 89, 19, 9, 30, 70, 4, 97, 22, 94, 73, 74, 76, 37, 92, 95, 36, 35, 17, 38, 79, 42, 25, 66, 55, 43, 33, 35, 63, 4, 37, 4, 42, 55, 31, 87, 81, 46, 66, 31, 40, 43, 25, 30, 55, 2, 6, 87, 16, 84, 90, 66, 20, 22, 53, 77, 21, 11, 42, 84, 77, 80, 2, 57, 83, 38, 2, 6, 94, 13, 70, 13, 89, 97, 14, 77, 39, 2, 68, 5, 80, 72, 29, 52, 41, 79, 0, 76, 92, 8, 33, 19, 39, 86, 99, 49, 95, 13, 37, 17, 85, 49, 67, 51, 42, 83, 96, 57, 36, 2, 94, 45, 100, 23, 89, 66, 96, 61, 6, 27, 56, 88, 60, 100, 32, 30, 14, 0, 33, 63, 3, 35, 20, 68, 26, 53, 88, 63, 12, 76, 97, 4, 29, 80, 88, 54, 84, 43, 77, 22, 96, 22, 37, 12, 36, 16, 7, 6, 34, 80, 93, 87, 48, 97, 29, 54, 28, 32, 47, 72, 99, 16, 81, 43, 52, 46, 82, 0, 70, 29, 31, 35, 12, 91, 51, 60, 23, 4, 12, 47, 63, 5, 14, 18, 37, 34, 74, 10, 36, 67, 51, 84, 18, 11, 100, 4, 25, 42, 44, 7, 74, 22, 3, 73, 74, 0, 47};
        optional = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0};

        System.out.println(solution(reward, health, optional));
//
//        System.out.println("=============================================================");
//        reward = new int[]{};
//        health = new int[]{};
//        optional = new int[]{};
//
//        System.out.println(solution(reward, health, optional));
//
//        System.out.println("=============================================================");
//        reward = new int[]{};
//        health = new int[]{};
//        optional = new int[]{};
//
//        System.out.println(solution(reward, health, optional));

    }
}
//            System.out.println("================================");
//            String b = optional[cur.bossNum] == 1 ? "선택" : "필수";
//            System.out.println("보스 번호 = " + cur.bossNum + " 필수/선택 = " + b);
//            System.out.println("현재 시간 = " + cur.time);
//            System.out.println("================================");

//
//37
//58
//130
//54
//122

//159
//564
//590
//534
//492