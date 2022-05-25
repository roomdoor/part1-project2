import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class T07 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        boolean[] lottoNumCheck = new boolean[46];
        int num;

        System.out.println("[로또 담청 프로그램\n");

        while (true) {
            System.out.print("로또 갯수를 입력해 주세요.(숫자 1 ~ 10) : ");
            num = sc.nextInt();

            if (num < 1 || num > 10) {
                System.out.println("1 ~ 10 사이의 숫자 값을 입력해 주세요");
            } else {
                break;
            }
        }

        int[][] arr = new int[num + 1][6];  //====================================== 로또 번호 저장 배열 // 마지막 행은 로또 발표 번호

        for (int i = 0; i < num; i++) {     // 로또 번호 랜덤으로 구하기 // 로또 구입 갯수 만큼 반복
            lottoNumCheck = new boolean[46];
            char alphabet = (char) (i + 'A');
            sb.append(alphabet).append("\t");

            for (int j = 0; j < 6; j++) {   // boolean 배열을 이용해서 중복되는 번호 없게 만듬
                int lottoNum = makeLottoNum(lottoNumCheck);
                lottoNumCheck[lottoNum] = true;
                arr[i][j] = lottoNum;
            }

            Arrays.sort(arr[i]);        // 작은 숫자 부터 나오도록 정렬
            String line2 = makeLottoNumLine(arr, i);
            sb.append(line2).append("\n");
        }

        String line3 = "\n[로또 발표]\n\t";
        sb.append(line3);
        lottoNumCheck = new boolean[46];
        for (int i = 0; i < 6; i++) {
            int lottoNum = makeLottoNum(lottoNumCheck);
            lottoNumCheck[lottoNum] = true;
            arr[num][i] = lottoNum;
        }

        Arrays.sort(arr[num]);
        String line4 = makeLottoNumLine(arr, num);
        sb.append(line4).append("\n");


        String line5 = "\n[내 로또 결과]\n";
        sb.append(line5);

        for (int i = 0; i < num; i++) {
            char alphabet = (char) (i + 'A');
            sb.append(alphabet).append("\t");

            String line6 = makeLottoNumLine(arr, i);
            sb.append(line6);

            int count = 0;
            int temp = 0;
            for (int j = 0; j < 6; j++) {
                for (int k = temp; k < 6; k++) {
                    if (arr[i][j] == arr[num][k]) {
                        count++;
                        temp = k;
                        break;
                    }
                }
            }
            String line7 = " => " + count + "개 일치\n";
            sb.append(line7);
        }

        System.out.println(sb.toString());

    }

    private static int makeLottoNum(boolean[] lottoNumCheck) { // 로또번호 1 ~ 45 까지 중복 없이 뽑기
        Random r = new Random();
        int lottoNum = r.nextInt(44) + 1;

        while (lottoNumCheck[lottoNum]) {
            lottoNum = r.nextInt(44) + 1;
        }

        return lottoNum;
    }

    private static String makeLottoNumLine(int[][] arr, int i) { // 로또번호 출력 만들기
        Arrays.sort(arr[i]);        // 작은 숫자 부터 나오도록 정렬
        return String.format("%02d,%02d,%02d,%02d,%02d,%02d"
                , arr[i][0], arr[i][1], arr[i][2], arr[i][3], arr[i][4], arr[i][5]);
    }
}
