public class T01 {
    public static void main(String[] args) {
        System.out.println("[구구단 출력] 줄 맞추기 위해 공백을 사용!!");

        for (int i = 1; i < 10; i++) {
            System.out.printf("[%d단] ", i);
            for (int j = 1; j < 10; j++) {
                String line = String.format("%1d X %1d = %2d   ", i, j, i * j);
                System.out.print(line);
            }
            System.out.println();
        }


        System.out.println("\n[구구단 출력] 줄 맞추기 위해 0을 사용!!");

        for (int i = 1; i < 10; i++) {
            System.out.printf("[%02d단] ", i);
            for (int j = 1; j < 10; j++) {
                String line = String.format("%02d X %02d = %02d   ", i, j, i * j);
                System.out.print(line);
            }
            System.out.println();
        }
    }
}
