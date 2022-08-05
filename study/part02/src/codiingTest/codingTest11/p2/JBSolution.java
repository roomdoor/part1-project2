package codiingTest.codingTest11.p2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JBSolution {
    public static int[] solutionArray(int n) {
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = String.valueOf(i + 1);
        }
        long b = System.nanoTime();
        Arrays.sort(arr);
        long a = System.nanoTime();
        System.out.println("Array 정렬 시간");
        System.out.println(a - b);

        return Arrays.stream(arr).mapToInt(Integer::parseInt).toArray();
    }

    public static int[] solutionList(int n) {
        List<String> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(String.valueOf(i + 1));
        }
        long b = System.nanoTime();
        Collections.sort(arr);
        long a = System.nanoTime();
        System.out.println("List 정렬 시간");
        System.out.println(a - b);


        return arr.stream().mapToInt(Integer::parseInt).toArray();
    }


    public static void main(String[] args) {
        long before = System.nanoTime();
        System.out.println(Arrays.toString(solutionArray(500)));
        long after = System.nanoTime();
        System.out.println(after - before);

        System.out.println();

        before = System.nanoTime();
        System.out.println(Arrays.toString(solutionList(500)));
        after = System.nanoTime();
        System.out.println(after - before);
    }
}
