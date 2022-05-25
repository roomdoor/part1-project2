import java.util.Random;
import java.util.Scanner;

public class T04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int year;
        int month;
        int day;
        String gender;

        System.out.println("[주민등록번호 계산]");
        while (true) {

            System.out.print("출생년도를 입력해 주세요. (yyyy) : ");
            year = sc.nextInt();
            sc.nextLine();

            if (year < 0) {
                System.out.println("올바른 년도 형식이 아닙니다. ex) 0 ~ ");
            } else {
                break;
            }
        }

        while (true) {
            System.out.print("출생월을 입력해 주세요. (mm) : ");
            month = sc.nextInt();
            sc.nextLine();

            if (month < 1 || month > 12) {
                System.out.println("올바른 월 형식이 아닙니다. ex) 1 ~ 12");
            } else {
                break;
            }

        }

        while (true) {
            System.out.print("출생일을 입력해 주세요. (dd) : ");
            day = sc.nextInt();
            sc.nextLine();

            if (day < 1 || day > 31) {
                System.out.println("올바른 일 형식이 아닙니다. ex) 0 ~ 31");
            } else {
                break;
            }
        }
        while (true) {
            System.out.print("성별을 입력해 주세요. (m/f) : ");
            gender = sc.nextLine();

            if (gender.equals("m") || gender.equals("f")) {
                break;
            } else {
                System.out.println("올바른 성별 형식이 아닙니다. ex) m/f");
            }
        }

        System.out.println(String.format("%06d", front(year, month, day)) + "-" + back(gender));
    }


    private static int front(int year, int month, int day) {
        int result = 0;
        result += year % 100 * 10000 + month * 100 + day;
        return result;
    }

    private static int back(String gender) {
        int result = 0;
        if (gender.equals("m")) {
            result += 3;
        } else {
            result += 4;
        }

        Random r = new Random();
        for (int i = 0; i < 6; i++) {
            result *= 10;
            result += r.nextInt(10);
        }
        return result;
    }
}
