package ch03.codingTest.p3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static ArrayList<Ameba>[] amebaTimeList;

    private static class Ameba {
        int time;
        boolean split;

        public Ameba() {
        }

        public Ameba(int time, boolean split) {
            this.time = time;
            this.split = split;
        }

    }

    public static int solution(int delay, int N) {
        int answer = 0;
        amebaTimeList = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            amebaTimeList[i] = new ArrayList<>();
        }

        amebaTimeList[0].add(new Ameba(0, true));
        for (int i = 0; i < amebaTimeList.length - 1; i++) {
            culAmebaCount(delay, N, i);
        }


        for (int i = 0; i < amebaTimeList.length; i++) {
            answer += amebaTimeList[i].size();
        }

        return answer;
    }

    public static void culAmebaCount(int delay, int N, int cur) {
        for (int i = 0; i < amebaTimeList[cur].size(); i++) {
            Ameba nowAmeba = amebaTimeList[cur].get(i);
            int nowAmebaTime = nowAmeba.time;

            if (nowAmeba.split) {
                amebaTimeList[cur + 1].add(new Ameba(0, true));
                amebaTimeList[cur + 1].add(new Ameba(0, false));
            } else {
                if (nowAmebaTime >= delay) {
                    amebaTimeList[cur + 1].add(new Ameba(nowAmebaTime - delay, true));
                    amebaTimeList[cur + 1].add(new Ameba(nowAmebaTime - delay, false));
                } else {
                    amebaTimeList[cur + 1].add(new Ameba(nowAmebaTime + 1, false));
                    amebaTimeList[cur].remove(nowAmeba);
                    i--;
                }
            }
        }
    }
}