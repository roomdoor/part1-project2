import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.Objects;
import java.util.Scanner;

public class T03 {
    public static void main(String[] args) {
        int age = -1;
        int time;
        String nationalMerit;
        String benefitCard;
        Scanner sc = new Scanner(System.in);

        System.out.println("[입장권 계산]");
        while (true) {
            System.out.print("나이를 입력해 주세요. (숫자) : ");
            age = sc.nextInt();
            if (age < 0) {
                System.out.println("올바른 나이 형식이 아닙니다. ex) 0 ~ ");
            } else {
                break;
            }
        }
        sc.nextLine();
        while (true) {
            System.out.print("입장 시간을 입력해 주세요. (숫자 입력) : ");
            time = sc.nextInt();
            sc.nextLine();
            if (time < 0 || time > 24) {
                System.out.println("올바른 시간 형식이 아닙니다. ex) 0 ~ 24");
            } else {
                break;
            }
        }
        while (true) {
            System.out.print("국가유공자 여부를 입력해 주세요.(y/n) : ");
            nationalMerit = sc.nextLine();

            if (nationalMerit.equals("y") || nationalMerit.equals("n")) {
                break;
            } else {
                System.out.println("올바른 입력값이 아닙니다. ex) y/n");
            }
        }

        while (true) {
            System.out.print("복지카드 여부를 입력해 주세요. (y/n) : ");
            benefitCard = sc.nextLine();
            if (benefitCard.equals("y") || benefitCard.equals("n")) {
                break;
            } else {
                System.out.println("올바른 입력값이 아닙니다. ex) y/n");
            }
        }

        System.out.println("입장료 : " + culAdmission(age, time, nationalMerit, benefitCard));

    }

    private static int culAdmission(int age, int time, String nationalMerit, String benefitCard) {
        int basic = 10000;
        if (age < 3) {
            return 0;
        }

        if (age < 13 || time >= 17) {
            return 4000;
        }

        if (Objects.equals(nationalMerit, "y") || Objects.equals(benefitCard, "y")) {
            return 8000;
        }

        return 10000;
    }
}
