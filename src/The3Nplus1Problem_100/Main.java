package The3Nplus1Problem_100;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    static Scanner in;

    private void run() {
        while (in.hasNext()) {
            int i = in.nextInt();
            int j = in.nextInt();

            int max, min;

            if (i <= j) {
                min = i;
                max = j;
            } else {
                min = j;
                max = i;
            }

            int maximumCycleLength = 0, actualCycleLength, n;

            for (int nCycles = min; nCycles <= max; nCycles++){
                n = nCycles;
                actualCycleLength = 1;

                while (n != 1) {
                    if (n % 2 != 0) n = 3 * n + 1;
                    else n /= 2;
                    actualCycleLength++;
                }

                if (actualCycleLength > maximumCycleLength) maximumCycleLength = actualCycleLength;
            }

            System.out.println(i + " " + j + " " + maximumCycleLength);

        }
    }

    public static void main(String[] args) throws IOException {
        in = new Scanner(System.in);
        new Main().run();
        in.close();
    }

}
