public class testclazz {
    public static void main(String[] args) {


        String str1 = "test";
        String str2 = "test";

        System.out.println(str1 == str2);       // true
        System.out.println(str1.equals(str2));  // true

        String s1 = new String("test");
        String s2 = new String("test");

        System.out.println(s1 == s2);           // false
        System.out.println(s1.equals(s2));      // true
    }
}
