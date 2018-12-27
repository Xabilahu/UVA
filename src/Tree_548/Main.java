package Tree_548;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/***********************************PSEUDOCODE*************************************
 * 
 * 1) Take the last element of Post-Order (this will be the root).
 * 2) Find it in In-Order, this divides the array in Left SubTree and Right SubTree
 * 3) Go to 1 with Left SubTree and Right SubTree
 * 
 **********************************************************************************/

public class Main{

    static BufferedReader in;

    private class BTN{

        int data;
        int actualSum;
        BTN left;
        BTN right;

        public BTN(int pData, BTN pLeft, BTN pRight) {
            this.data = pData;
            this.actualSum = 0;
            this.left = pLeft;
            this.right = pRight;
        }

    }

    private void run() {

        try {
            while (in.ready()) {
                String inOrderStr = in.readLine();
                String postOrderStr = in.readLine();
                String[] inOrderNums = inOrderStr.split("\\s+");
                String[] postOrderNums = postOrderStr.split("\\s+");
                int[] inOrder = new int[inOrderNums.length];
                int[] postOrder = new int[postOrderNums.length];
                for (int i = 0; i < inOrder.length; i++) {
                    inOrder[i] = Integer.parseInt(inOrderNums[i]);
                    postOrder[i] = Integer.parseInt(postOrderNums[i]);
                }
                this.proccessTree(this.createTree(inOrder, postOrder));
            }
        } catch (Exception e) {e.printStackTrace();}

    }

    private BTN createTree(int[] inOrder, int[] postOrder){
        if (inOrder.length == 0) return null;
        else {
            int length = postOrder.length - 1;
            int node = postOrder[length], divideBy = length;
            for (int i = 0; i < length; i++) {
                if (inOrder[i] == node) {
                    divideBy = i;
                    break;
                }
            }
            int[] newInOrderLeft = new int[divideBy];
            int[] newInOrderRight = new int[length - divideBy];
            int[] newPostOrderLeft = new int[divideBy];
            int[] newPostOrderRight = new int[length - divideBy];
            boolean changeLeftRight = false;
            for (int i = 0; i < postOrder.length; i++) {
                if (inOrder[i] == node) {
                    changeLeftRight = true;
                    continue;
                }
                if (!changeLeftRight) {
                    newInOrderLeft[i] = inOrder[i];
                    newPostOrderLeft[i] = postOrder[i];
                } else {
                    newInOrderRight[i - (divideBy + 1)] = inOrder[i];
                    newPostOrderRight[i - (divideBy + 1)] = postOrder[i - 1];
                }
            }
            return new BTN(node, this.createTree(newInOrderLeft, newPostOrderLeft), this.createTree(newInOrderRight, newPostOrderRight));
        }
    }

    private void proccessTree(BTN node) {
        System.out.println(this.decideLeastValuePath(node));
    }

    private int decideLeastValuePath(BTN actualNode) {
        Stack<BTN> toExamine = new Stack<>();
        int leafValue = Integer.MAX_VALUE;
        int minSum = Integer.MAX_VALUE;

        toExamine.push(actualNode);

        while (!toExamine.isEmpty()) {
            BTN actual = toExamine.pop();
            actual.actualSum += actual.data;
            if (this.isLeaf(actual)) {
                if (minSum > (actual.actualSum)) {
                    minSum = actual.actualSum;
                    leafValue = actual.data;
                } else if (minSum == (actual.actualSum) && leafValue > actual.data) leafValue = actual.data;
            } else {
                if (actual.left != null) {
                    actual.left.actualSum = actual.actualSum;
                    toExamine.push(actual.left);
                }
                if (actual.right != null) {
                    actual.right.actualSum = actual.actualSum;
                    toExamine.push(actual.right);
                }
            }
        }

        return leafValue;
    }

    private boolean isLeaf(BTN node) {
        if (node == null) return false;
        else return node.left == null && node.right == null;
    }

    public static void main(String[] args) throws Exception {
        in = new BufferedReader(new InputStreamReader(System.in));
        new Main().run();
        in.close();
    }

}