package ch02.LinearDS11;

import java.util.function.Function;

public class TTT {
    public static void main(String args[]) {
        Function<String, Double> doubleConvertor = Double::parseDouble;
        Function<String, Double> doubleConvertorLambda = (String s) -> Double.parseDouble(s);
        System.out.println("double value using method reference - " + doubleConvertor.apply("0.254"));
        System.out.println("double value using Lambda - " + doubleConvertorLambda.apply("0.254"));
    }
}
