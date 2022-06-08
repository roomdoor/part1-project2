import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Note {
    public static void main(String[] args) {
//        ------------------------------------------------------------------------------------
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

        Integer[] arrInteger = Arrays.stream(a).boxed().toArray(Integer[]::new);
        List<Integer> arrList = Arrays.stream(a).boxed().collect(Collectors.toList());
        Set<Integer> arrSet = Arrays.stream(a).boxed().collect(Collectors.toSet());

        System.out.println(Arrays.toString(flatArray));
        System.out.println(Arrays.toString(flatArray1));

//        ------------------------------------------------------------------------------------
        Function<String, Double> doubleConvertor = Double::parseDouble;
        Function<String, Double> doubleConvertorLambda = (String s) -> Double.parseDouble(s);
        System.out.println("double value using method reference - " + doubleConvertor.apply("0.254"));
        System.out.println("double value using Lambda - " + doubleConvertorLambda.apply("0.254"));
    }
}
