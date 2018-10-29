package CostCutting_11727;

import java.util.Scanner;

public class Main {

    static Scanner in;

    public void run() {
        int n = in.nextInt();
        for (int i = 1; i <= n; i++) {
            int n1 = in.nextInt();
            int n2 = in.nextInt();
            int n3 = in.nextInt();

            int medium;
            if (n1 <= n2)
                if (n1 <= n3) // n1 lowest
                    if (n2 <= n3) medium = n2;
                    else medium = n3;
                else medium = n2; // n1 <= n2 < n3
            else // n2 < n1
                if (n1 <= n3) // n2 lowest
                    medium = n1;
                else // n2 < n1, n3 < n1
                    if (n2 <= n3) medium = n3;
                    else medium = n2;

            System.out.println("Case " + i + ": " + medium);
        }
    }

    public static void main(String[] args) throws Exception {
        in = new Scanner(System.in);
        new Main().run();
        in.close();
    }
}
