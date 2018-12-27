package TreeSumming_112;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main{

    static BufferedReader in;

    private class BTN{

        int data;
        BTN left;
        BTN right;

        public BTN(int pData, BTN pLeft, BTN pRight) {
            this.data = pData;
            this.left = pLeft;
            this.right = pRight;
        }

    }

    private void run() {

        try {
            boolean firstInputLine = true;
            int sum = 0;
            StringBuilder tree = new StringBuilder();
            Stack<Character> parenthesis = new Stack<>();
            while (in.ready()) {
                String line = in.readLine();
                if (firstInputLine) {
                    firstInputLine = false;
                    String[] chars = line.split("\\s+");
                    sum = Integer.parseInt(chars[0]);
                    StringBuilder startOfTree = new StringBuilder(); //Following lines get the line skipping the objective sum
                    for (int i = 1; i < chars.length; i++) 
                        startOfTree.append(chars[i]);
                    line = startOfTree.toString(); //End of getting the line skipping the objective sum, this only has to be done in the first line of every tree
                } else line = line.replaceAll("\\s+", "");
                tree.append(line);
                if (this.isCorrect(line,parenthesis)) {
                    this.proccessTree(tree.toString(), sum);
                    tree = new StringBuilder();
                    parenthesis = new Stack<>();
                    firstInputLine = true;
                } else continue; //If not correct, tree follows on next line
            }
        } catch (Exception e) {}

    }

    private boolean isCorrect(String tree, Stack<Character> parenthesis) {
        for (int i = 0; i < tree.length(); i++){
            if (tree.charAt(i) == '(') parenthesis.push('(');
            else if (tree.charAt(i) == ')') parenthesis.pop();
        }
        return parenthesis.isEmpty();
    }

    private void proccessTree(String tree, int sum) {
        if (this.treeSums(this.createTree(tree), sum)) System.out.println("yes");
        else System.out.println("no");
    }

    private BTN createTree(String tree) {
        if (tree.equals("()")) return null;
        else {
            int data = Integer.parseInt(tree.split("[\\(||\\)]")[1]);
            String left = "", right = "";
            int count = 0, indexPar = 0;
            boolean firstPar = false;
            for (int i = 2; i < tree.length(); i++) { //get the two subTrees left and right
                if (tree.charAt(i) == '(') {
                    count++;
                    if (!firstPar) {
                        indexPar = i;
                        firstPar = true;
                    }
                } else if (tree.charAt(i) == ')') count--;
                if (count == 0 && firstPar) {
                    left = tree.substring(indexPar, i+1);
                    right = tree.substring(i+1, tree.length()-1);
                    break;
                }
            }
            return new BTN(data,this.createTree(left),this.createTree(right));
        }
    }

    private boolean treeSums(BTN actualNode, int sum) {
        if (actualNode == null) return false;
        else {
            if (this.sumTree(actualNode, sum, 0) == sum) return true;
            else return false;
        }
    }

    private int sumTree(BTN actualNode, int objectiveSum, int actualSum) {
        if (actualNode == null) return actualSum;
        else if (this.isLeaf(actualNode)) return actualSum + actualNode.data;
        else {
            if (actualNode.left != null) {
                int leftEval = this.sumTree(actualNode.left, objectiveSum, actualSum + actualNode.data);
                if (leftEval == objectiveSum) return leftEval;
            } 
            if (actualNode.right != null) return this.sumTree(actualNode.right, objectiveSum, actualSum + actualNode.data);
            return Integer.MIN_VALUE;
        }
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