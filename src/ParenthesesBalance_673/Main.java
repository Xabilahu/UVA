package ParenthesesBalance_673;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EmptyStackException;
import java.util.Stack;

public class Main {

    static BufferedReader in;

    private void run() {
        try {
            int nTest = Integer.parseInt(in.readLine());
            for (int i = 0; i < nTest; i++) {
                String line = in.readLine();
                Stack<Character> stk = new Stack<>();
                boolean mismatch = false;
                int j = 0;
                try {
                    while (j < line.length() && !mismatch) {
                        Character actualChar = line.charAt(j++);
                        switch (actualChar) {
                            case '(':
                            case '[':
                                stk.push(actualChar);
                                break;
                            case ')':
                                if (!stk.pop().equals('(')) mismatch = true;
                                break;
                            default:
                                if (!stk.pop().equals('[')) mismatch = true;
                                break;
                        }
                    }
                    if (!mismatch && stk.empty()) System.out.println("Yes");
                    else System.out.println("No");
                } catch (EmptyStackException e) {
                    System.out.println("No");
                }
            }
        } catch (IOException e) {}
    }

    public static void main(String[] args) throws Exception {
        in = new BufferedReader(new InputStreamReader(System.in));
        new Main().run();
        in.close();
    }

}
