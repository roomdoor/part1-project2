import java.util.Scanner;

public class T08 {
    public static void main(String[] args) {

        System.out.println("[과세금액 계산 프로그램]");
        System.out.print("연소득을 입력해 주세요. : ");
        Scanner sc = new Scanner(System.in);

        long money = sc.nextLong();
        long tax;
        long nextMoney = money;
        int temp = 0;
        long totalTax = 0;
        long nTax = 0;
        long[][] taxArr = {{12000000, 46000000, 88000000, 150000000, 300000000, 500000000, 1000000000, nextMoney},
                {6, 15, 24, 35, 38, 40, 42, 45},
                {0, 1080000, 5220000, 14900000, 19400000, 25400000, 35400000, 65400000}};

        while (temp < 8) {
            if (money > taxArr[0][temp]) {

                if (temp > 0) {
                    nextMoney = money - taxArr[0][temp];
                    tax = (taxArr[0][temp] - taxArr[0][temp - 1]) / 100 * taxArr[1][temp];
                    System.out.printf("%10d * %3d%s = %10d\n", taxArr[0][temp] - taxArr[0][temp - 1], taxArr[1][temp], "%", tax);
                } else {
                    tax = taxArr[0][temp] / 100 * taxArr[1][temp];
                    System.out.printf("%10d * %3d%s = %10d\n", taxArr[0][temp], taxArr[1][temp], "%", tax);
                }
                totalTax += tax;
                temp++;

            } else {
                tax = nextMoney / 100 * taxArr[1][temp];
                totalTax += tax;
                System.out.printf("%10d * %3d%s = %10d\n", nextMoney, taxArr[1][temp], "%", tax);
                nTax = (money / 100 * taxArr[1][temp]) - taxArr[2][temp];
                break;
            }
        }

        System.out.printf("[세율에 의한 세금]:\t\t\t %10d\n", totalTax);
        System.out.printf("[누진공제 계산에 의한 세금]:\t %10d\n", nTax);

    }
}
