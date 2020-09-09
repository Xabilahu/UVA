package BlocksProblem_101;

import java.util.Scanner;
import java.util.Stack;

public class Main {

    private static Scanner in;
    private Stack<Integer>[] st;
    private int[] stackIndexes;

    @SuppressWarnings("unchecked")
    public void run() {
        Stack<Integer> tmp;
        int a, b;
        int length = Integer.parseInt(in.nextLine());

        st = (Stack<Integer>[]) new Stack[length];
        stackIndexes = new int[length];

        for (int i = 0; i < length; i++) {
            st[i] = new Stack<Integer>();
            st[i].push(i);
            stackIndexes[i] = i;
        }

        while (in.hasNext()){
            String[] line = in.nextLine().split(" ");
            if (line[0].equals("move")) {
                a = Integer.parseInt(line[1]);
                b = Integer.parseInt(line[3]);

                if (a != b && stackIndexes[a] != stackIndexes[b]) {
                    backToPositions(removeFromTop(a));
                    if (line[2].equals("onto")) 
                        backToPositions(removeFromTop(b));
                        
                    st[stackIndexes[a]].pop();
                    stackIndexes[a] = stackIndexes[b];
                    st[stackIndexes[b]].push(a);
                }
            } else if (line[0].equals("pile")) {
                a = Integer.parseInt(line[1]);
                b = Integer.parseInt(line[3]);

                if (a!= b && stackIndexes[a] != stackIndexes[b]) {
                    if (line[2].equals("onto"))
                        backToPositions(removeFromTop(b));
                    tmp = removeFromTop(a);
                    st[stackIndexes[a]].pop();
                    stackIndexes[a] = stackIndexes[b];
                    st[stackIndexes[b]].push(a);
                    moveToPosition(stackIndexes[b], tmp);
                }
            } else { // quit command
                StringBuilder output = new StringBuilder();
                for (int i = 0; i < length; i++) {
                    StringBuilder sb = new StringBuilder();
                    while (!st[i].empty()) {
                        sb.insert(0, " " + st[i].pop());
                    }
                    sb.insert(0, i + ":");
                    output.append(sb.toString() + "\n");
                }
                System.out.print(output.toString());
            }
        }
    }

    private Stack<Integer> removeFromTop(int number) {
        Stack<Integer> reversedStack = new Stack<Integer>();
        int removedItem = -1;

        while (true) {
            removedItem = st[stackIndexes[number]].pop();
            if (removedItem != number) reversedStack.push(removedItem);
            else {
                st[stackIndexes[number]].push(removedItem);
                break;
            }
        }

        return reversedStack;
    }

    private void backToPositions(Stack<Integer> items) {
        while (!items.empty()) {
            int poppedItem = items.pop();
            stackIndexes[poppedItem] = poppedItem;
            st[poppedItem].push(poppedItem);
        }
    }

    private void moveToPosition(int position, Stack<Integer> items) {
        while(!items.empty()) {
            int poppedItem = items.pop();
            stackIndexes[poppedItem] = position;
            st[position].push(poppedItem);
        }
    }

    public static void main(String[] args) {
        in = new Scanner(System.in);
        new Main().run();
        in.close();
    }
}
