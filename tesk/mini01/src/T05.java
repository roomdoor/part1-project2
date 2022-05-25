import java.time.LocalDate;
import java.util.Scanner;

public class T05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String year;
        String month;
        System.out.println("[달력 출력 프로그램]");

        while (true) {

            System.out.print("달력의 년도를 입력해 주세요. (yyyy) : ");
            year = sc.nextLine();

            if (Integer.parseInt(year) < 1) {
                System.out.println("올바른 년도 형식이 아닙니다. ex) yyyy");
            } else {
                break;
            }
        }

        while (true) {

            System.out.print("달력의 월을 입력해 주세요. (mm) : ");
            month = sc.nextLine();

            if (Integer.parseInt(month) < 1 || Integer.parseInt(month) > 12) {
                System.out.println("올바른 월 형식이 아닙니다. ex) mm");
            } else {
                break;
            }
        }

        makeCalendar(year, month);


    }

    private static void makeCalendar(String year, String month) {
        StringBuilder result = new StringBuilder();
        int y = Integer.parseInt(year);
        int m = Integer.parseInt(month);
        LocalDate date = LocalDate.of(y, m, 1);

        // [yyyy년 mm월] ======================================================
        String title = String.format("[%d년 %02d월]\n", y, m);
        result.append(title);

        // 일 월 화 수 목 금 토 ==================================================
//        String week = String.format("%s %3s %3s %3s %3s %3s %3s\n", "일", "월", "화", "수", "목", "금", "토");
//        한글 <-> 숫자 영어 크기 달라서 줄이 안맞음
        String week = "일\t월\t화\t수\t목\t금\t토\n"; // 탭으로 일정간격 유지하기로 결정
        result.append(week);

        // 1일 이전 공백 추가 ==================================================
        int firstDayBlock = (date.getDayOfWeek().getValue() % 7);   // 1일 이전 빈 공간 구하기 // 일요일 = 7 이라서 7로 나눈 나머지로 공백 간격계산
        for (int i = 0; i < firstDayBlock; i++) {                   // 공백 간격만므 \t 추가
            result.append("\t");
        }
        result.append("01").append("\t");                          // 1일 달력에 추가
        if (firstDayBlock == 6) {                                   // 토요일이면 줄바꿈 추가
            result.append("\n");
        }


        // 2일부터 그 월 마지막 일까지 추가 ========================================
        for (int i = 2; i <= date.lengthOfMonth(); i++) {           // 2일 부터 달력에 추가
            date = LocalDate.of(y, m, i);
            String iday = String.format("%02d", i);
            result.append(iday).append("\t");
            if (date.getDayOfWeek().getValue() % 6 == 0) {          // 토요일이면 줄바꿈 추가
                result.append("\n");
            }
        }

        // 달력 출력 ==========================================================
        System.out.println(result);
    }

    private static void practice() {
//        int mf = LocalDate.MAX.getDayOfMonth();
//        System.out.println("mf = " + mf);
//        DayOfWeek dwMax = LocalDate.MAX.getDayOfWeek();
//        System.out.println("dwMax = " + dwMax);
//        DayOfWeek dwMin = LocalDate.MAX.getDayOfWeek();
//        System.out.println("dwMin = " + dwMin);
//        DayOfWeek dw = LocalDate.now().getDayOfWeek();
//        System.out.println("dw = " + dw);
//
//        LocalDate date = LocalDate.of(2022, 5, 12);
//        System.out.println(date.getDayOfWeek());
//        System.out.println(date.getDayOfMonth());
//        System.out.println(date.getDayOfYear());
//        System.out.println(date.lengthOfMonth());

    }
}
