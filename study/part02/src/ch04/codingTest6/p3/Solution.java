package ch04.codingTest6.p3;


import java.util.HashMap;

public class Solution {
    public static int solution(String[] ingredients, String[] items) {
        if (ingredients.length == items.length) {
            return items.length;
        }

        int[] count = new int[ingredients.length];
        boolean[] check = new boolean[items.length];

        HashMap<String, Integer> set = new HashMap<>();
        for (int i = 0; i < ingredients.length; i++) {
            set.put(ingredients[i], i);
        }

        int start = items.length - 1;
        int end = 0;

        for (int i = 0; i < items.length; i++) {
            if (set.containsKey(items[i])) {
                count[set.get(items[i])]++;
                check[i] = true;

                start = Math.min(start, i);
                end = Math.max(end, i);
            }
        }

        boolean s = true;
        boolean e = true;
        while (s || e) {
            if (count[set.get(items[start])] - 1 > 0) {
                count[set.get(items[start])] -= 1;
                while (true) {
                    if (check[++start]) {
                        break;
                    }
                }
            } else {
                s = false;
            }

            if (count[set.get(items[end])] - 1 > 2) {
                count[set.get(items[end])] -= 1;
                while (true) {
                    if (check[--end]) {
                        break;
                    }
                }
            } else {
                e = false;
            }

        }
        return end - start + 1;


    }

    public static void main(String[] args) {
        String[] a = new String[]{"우유", "시리얼", "우유", "우유"};
        String[] b = new String[]{"우유", "물", "위스키", "막걸리", "소주", "김치냉장고", "시리얼"};

        System.out.println(solution(a, b));

        a = new String[]{"생닭", "인삼", "소주", "대추"};
        b = new String[]{"물", "커피", "생닭", "소주", "사탕", "생닭", "대추", "쌀", "물", "인삼", "커피", "생닭", "소주", "사탕", "생닭", "대추", "쌀", "물", "인삼", "커피", "생닭", "소주", "사탕", "생닭", "대추", "쌀"};

        System.out.println(solution(a, b));

        a = new String[]{"a", "b", "c"};
        b = new String[]{"a", "k", "k", "k", "k", "k", "k", "k", "b", "c",
                "a", "k", "k", "k", "b", "c", "k", "k", "k", "k",
                "a", "k", "k", "k", "k", "k", "k", "k", "b", "c",
                "a", "k", "k", "k", "b", "c"};
//        System.out.println(solution(a, b));

        a = new String[]{"a", "a"};
        b = new String[]{"a", "1", "a"};
        System.out.println(solution(a, b));
    }


}
