package ch03.codingTest4.p1;

import java.io.*;
import java.util.*;

public class newSolution {
    private class Node {
        char st;
        Map<Character, Node> children;
        List<String> songs;

        public Node(char st) {
            this.st = st;
            children = new HashMap<Character, Node>();
            songs = new ArrayList<>();
        }
    }

    private class Trie {
        Node root = new Node('\n');

        public void addLyrics(String lyrics, String title) {
            Node cur = root;
            for (int i = 0; i < lyrics.length(); i++) {
                char next = lyrics.charAt(i);
                if (!cur.children.containsKey(next)) {
                    cur.children.put(next, new Node(next));
                }
                cur = cur.children.get(next);
                cur.songs.add(title);
            }
        }

        public List<String> getResult(String problem) {
            Node cur = root;
            for (int i = 0; i < problem.length(); i++) {
                char next = problem.charAt(i);
                if (cur.children.containsKey(next)) {
                    cur = cur.children.get(next);
                } else {
                    return new ArrayList<>();
                }
            }
            return cur.songs;
        }
    }

    public String[][] solution(String[] titles, String[] lyrics, String[] problems) {
        String[][] answer = new String[problems.length][];
        Trie trie = new Trie();
        for (int i = 0; i < lyrics.length; i++) {
            trie.addLyrics(lyrics[i], titles[i]);
        }

        for (int i = 0; i < problems.length; i++) {
            answer[i] = trie.getResult(problems[i]).toArray(new String[0]);
        }
        return answer;
    }

    public static void testCase(String tc, String[][] cc) {
        int caseCount = 0;
        boolean in = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tc.length(); i++) {
            if (tc.charAt(i) == '[') {
                sb = new StringBuilder();
                in = false;
            } else if (tc.charAt(i) == ']') {
                in = true;
                cc[caseCount] = sb.toString().split(" ");
                caseCount++;
            } else if (!(tc.charAt(i) == ',' && in || tc.charAt(i) == '"' || tc.charAt(i) == ',')) {
                sb.append(tc.charAt(i));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        newSolution sol = new newSolution();
        String[] a = {"아모르파티", "아기상어", "올챙이와개구리", "산다는건"};
        String[] b = {"산다는게다그런거지누구나빈손으로와...(후략)", "아기상어뚜루루뚜루귀여운뚜루루뚜루...(후략)", "개울가에올챙이한마리꼬물꼬물헤엄치다...(후략)", "산다는건다그런거래요힘들고아픈날도많지만...(후략)"};
        String[] c = {"산다", "아기상어", "올챙이"};


        BufferedReader br = new BufferedReader(new FileReader("/Users/isihwa/workspace/zerobase/강의자료/0616/tc/problem1/eff_test/5_i.txt"));
        String str;
        StringBuilder sb = new StringBuilder();
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        String t = sb.toString();

        br = new BufferedReader(new FileReader("/Users/isihwa/workspace/zerobase/강의자료/0616/tc/problem1/eff_test/5_o.txt"));
        sb = new StringBuilder();
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        String r = sb.toString();


        String[][] cc = new String[][]{a, b, c};
        testCase(t, cc);

        String[][] rr = new String[cc[2].length][];
        testCase(r, rr);

        long bt = System.currentTimeMillis();
        String[][] cul = sol.solution(cc[0], cc[1], cc[2]);
        long at = System.currentTimeMillis();
        System.out.println("걸린 시간 = " + (at - bt));

        if (Arrays.deepEquals(rr, cul)) {
            System.out.println("OK");
        } else {
            System.out.println("NO");
        }

    }
}