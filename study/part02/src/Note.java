import java.util.Arrays;
import java.util.function.IntFunction;
import java.util.stream.Stream;

public class Note {
    public static void main(String[] args) {

        int a [] = {1,2,6,7,2};
        int b [] = {2,44,55,2};
        int c [] = {2,44,511,33};


        int originalArray [][] = new int[][]{a,b,c};

        int[] flatArray = Arrays.stream(originalArray)
                .flatMapToInt(Arrays::stream)
                .toArray();

        int[] flatArray1 = Stream.of(a, b, c)
                .flatMapToInt(Arrays::stream)
                .toArray();

        Integer[] arr = Arrays.stream(a).boxed().toArray(Integer[]::new);

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(flatArray));
        System.out.println(Arrays.toString(flatArray1));
    }
}
