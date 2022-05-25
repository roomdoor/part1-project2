import java.util.Arrays;
import java.util.Random;

public class T06 {
    public static void main(String[] args) {

        int a = 10000;
        String[] candidate = {"이재명", "윤석열", "심상정", "안철수"};                  // 후보 배열
        int[] voteNum = new int[4];                                              // 득표 수 배열

        for (int i = 1; i <= a; i++) { //============================================= 4명중 한명 투표
            Random r = new Random();
            int num = r.nextInt(4);
            voteNum[num]++;
            StringBuilder sb = new StringBuilder();

            String line = "[투표진행율]: " + String.format("%.2f", i / 100.0) + "%, " + i + "명 투표 => " + candidate[num];
            String line1 = "[기호:1]: 이재명: " + String.format("%05.2f", voteNum[0] / 100.0) + "%, (투표수: " + voteNum[0] + ")";
            String line2 = "[기호:2]: 윤석열: " + String.format("%05.2f", voteNum[1] / 100.0) + "%, (투표수: " + voteNum[1] + ")";
            String line3 = "[기호:3]: 심상정: " + String.format("%05.2f", voteNum[2] / 100.0) + "%, (투표수: " + voteNum[2] + ")";
            String line4 = "[기호:4]: 안철수: " + String.format("%05.2f", voteNum[3] / 100.0) + "%, (투표수: " + voteNum[3] + ")";
            sb.append(line).append("\n");
            sb.append(line1).append("\n");
            sb.append(line2).append("\n");
            sb.append(line3).append("\n");
            sb.append(line4).append("\n");

            if (i == a) { //========================================== 10000표 투표 후 최다 득표자 선출, 동율 일 경우 체크
                int win = 0;
                int same = -1;
                for (int j = 1; j < 4; j++) {
                    if (voteNum[win] < voteNum[j]) {
                        win = j;
                    } else if (voteNum[win] == voteNum[j]) {
                        win = j;
                        same = j;
                    }
                }

                String lineLast;
                if (win == same) {
                    lineLast = "최다 득표자가 동율입니다.";
                } else {
                    lineLast = "[투표결과] 당선인: " + candidate[win];
                }
                sb.append(lineLast);
            }
            System.out.println(sb);
        }
    }
}
