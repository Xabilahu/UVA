package SimpleEquations_11565;

import java.util.Scanner;

public class Main {

    static Scanner in;

    private void calculate(int A, int B, int C) {
        for (int x = -22; x <= 22; x++)
            for (int y = -100; y <= 100; y++)
                for (int z = -100; z <= 100; z++)
                    if (((x+y+z)==A) && (x != y) && (y != z) && (x != z) && ((x*y*z)==B) && ((x*x+y*y+z*z)==C)) {
                        System.out.println(x + " " + y + " " + z);
                        return;
                    }
        System.out.println("No solution.");
    }

    private void run() {
        int n = in.nextInt();
        for (int i = 1; i <= n; i++) {
            int A = in.nextInt();
            int B = in.nextInt();
            int C = in.nextInt();

            calculate(A, B, C);
        }
    }

    public static void main(String[] args) throws Exception {
        in = new Scanner(System.in);
        new Main().run();
        in.close();
    }
}