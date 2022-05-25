import java.util.Scanner;

public class T02 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("[캐시백 계산]");
            System.out.print("결제 금액을 입력해 주세요. (금액) : ");
            int cash = sc.nextInt();

            System.out.println("결제 금액은 " + cash + "원이고, 캐시백은 " + cashCul(cash) + "원 입니다.\n");
        }
    }

    public static int cashCul(int cash) {
        if (cash > 3000) {
            return 300;
        } else {
            return cash / 10;
        }
    }
}
