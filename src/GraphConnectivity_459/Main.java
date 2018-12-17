package GraphConnectivity_459;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static BufferedReader in;

    private void run() {
        try {
            if (in.ready()) {
                final int OFFSET = 10;
                String line = in.readLine();
                String[] number = line.split("\\D+");
                int numInputs = Integer.parseInt(number[0]);
                in.readLine();
                for (int i = 0; i < numInputs; i++) {
                    int numNodes = Character.getNumericValue(in.readLine().charAt(0)) - OFFSET + 1; //A gets value of 10, every char is off by that value
                    ArrayList<Integer>[] adjList = (ArrayList<Integer>[]) new ArrayList[numNodes];
                    for (int j = 0; j < adjList.length; j++) 
                        adjList[j] = new ArrayList<>();
                    line = in.readLine();
                    while (line != null && !line.equals("")) {
                        int startNode = Character.getNumericValue(line.charAt(0)) - OFFSET;
                        int endNode = Character.getNumericValue(line.charAt(1)) - OFFSET;
                        adjList[startNode].add(endNode);
                        adjList[endNode].add(startNode);
                        line = in.readLine();
                    }
                    System.out.println(this.processGraph(adjList));
                    if (i != numInputs - 1) System.out.println();
                }
            }
        } catch(IOException e) {}
    }

    private int processGraph(ArrayList<Integer>[] adjList) {
        boolean[] checked = new boolean[adjList.length];
        int maxSubgraphs = 0;
        boolean end = false;
        int i = 0;

        while (!end) {
            while (i >= 0 && i < checked.length && checked[i]) i++;
            if (i == checked.length) {
                end = true;
                if (!checked[i-1]) maxSubgraphs++;
            }
            else this.processGraph(adjList, checked, i);
            maxSubgraphs++;
        }
        return maxSubgraphs - 1;
    }

    private void processGraph(ArrayList<Integer>[] adjList, boolean[] checked, int actualNode) {
        checked[actualNode] = true;
        for (int x : adjList[actualNode])
            if (!checked[x]) this.processGraph(adjList, checked, x);
    }

    public static void main(String[] args) throws Exception{
        in = new BufferedReader(new InputStreamReader(System.in));
        new Main().run();
        in.close();
    }

}