package TheForrestForTheTrees_599;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

public class Main {

    private static BufferedReader in;
    private LinkedHashMap<Character,ArrayList<Character>> adjList;
    private int numNodes;

    private void run() {

        try {
            if (in.ready()) {
                int numTests = Integer.parseInt(in.readLine());
                for (int i = 0; i < numTests; i++) {
                    this.adjList = new LinkedHashMap<>();
                    String line = " ";
                    while (line.charAt(0) != '*') {
                        line = in.readLine();
                        if (line.charAt(0) != '*'){
                            if (!this.adjList.containsKey(line.charAt(1))) {
                                ArrayList<Character> adj = new ArrayList<>();
                                adj.add(line.charAt(3));
                                this.adjList.put(line.charAt(1), adj);
                            } else this.adjList.get(line.charAt(1)).add(line.charAt(3));
                            if (!this.adjList.containsKey(line.charAt(3))) this.adjList.put(line.charAt(3), new ArrayList<>());
                        }
                    }
                    String[] nodes = in.readLine().split(",");
                    this.numNodes = nodes.length;
                    int acorns = 0;
                    for (String s : nodes) 
                        if (!this.adjList.containsKey(s.charAt(0))) {
                            this.adjList.put(s.charAt(0), new ArrayList<>());
                            acorns++;
                        }
                    System.out.println("There are " + (this.numTrees() - acorns) + " tree(s) and " + acorns + " acorn(s).");
                }
            }
            System.out.println();
        } catch (Exception e) {}

    }

    private int numTrees() {
        int numCycles = 0, treeAmount = 0;
        Stack<Character> toExamine = new Stack<>();
        HashSet<Character> examined = new HashSet<>();
        boolean notATree;
        Iterator<Character> everyNode = this.adjList.keySet().iterator();
        while (numCycles < this.numNodes) {
            notATree = false;
            Character next;
            do {
                next  = everyNode.next();
            } while (examined.contains(next));
            toExamine.push(next);
            examined.add(next);
            while (!toExamine.isEmpty()) {
                Character actual = toExamine.pop();
                for (Character c : this.adjList.get(actual))
                    if (!examined.contains(c)) {
                        toExamine.push(c);
                        examined.add(c);
                    } else notATree = true;
                numCycles++;
            }
            if (!notATree) treeAmount++;
        }
        return treeAmount;
    }

    public static void main(String[] args) throws Exception {
        in = new BufferedReader(new InputStreamReader(System.in));
        new Main().run();
        in.close();
    }

}
