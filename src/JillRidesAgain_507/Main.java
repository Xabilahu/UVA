package JillRidesAgain_507;

import java.util.Scanner;

public class Main {

    static Scanner in;

    private void run() {
        int nTest = in.nextInt();
        for (int i = 1; i <= nTest; i++) {
            int s = in.nextInt();
            int potentialStart = 0, maxSoFar = Integer.MIN_VALUE, maxEndingHere = 0, posStart = 0, posEnd = 0;
            for (int j = 1; j < s; j++)
            {
                maxEndingHere += in.nextInt();

                if (maxSoFar < maxEndingHere || (maxSoFar == maxEndingHere && (j+1) - potentialStart > posEnd - posStart))
                {
                    maxSoFar = maxEndingHere;
                    posStart = potentialStart;
                    posEnd = j + 1;
                }

                if (maxEndingHere < 0)
                {
                    maxEndingHere = 0;
                    potentialStart = j + 1;
                }
            }

            if (maxSoFar < 0) System.out.println("Route " + i + " has no nice parts");
            else System.out.println("The nicest part of route " + i + " is between stops " + posStart + " and " + posEnd);
        }
    }

    public static void main(String[] args) throws Exception {
        in = new Scanner(System.in);
        new Main().run();
        in.close();
    }
}
