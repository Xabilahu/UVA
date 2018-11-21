package TeamQueue_540;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    Queue<Queue<Integer>> q;
    HashMap<Integer, Integer> teams; //Key: people, Value: team ID
    HashMap<Integer, Queue<Integer>> queue; // Key: team ID, Value: queue

    static Scanner in;


    public Main() {
        initialize();
    }

    private void initialize() {
        q = (Queue<Queue<Integer>>) new LinkedList();
        teams = new HashMap<Integer, Integer>();
        queue = new HashMap<Integer, Queue<Integer>>();
    }

    public void run() {
        int testCaseNumber = 1;
        String firstLine = in.nextLine();
        int nTeams = Integer.parseInt(firstLine);
        while (nTeams != 0) { // read test case
            initialize();
            int team = 0;

            for (int i = 1; i <= nTeams; i++) { // read teams
                String line = in.nextLine();
                String[] teamArray = line.split("\\s+");
                int nMembers = Integer.parseInt(teamArray[0]);
                for (int j = 1; j <= nMembers; j++) {
                    int member = Integer.parseInt(teamArray[j]);
                    teams.put(member, team);
                }
                team++; // new team for the next line
            }
            processTestCase(testCaseNumber);
            testCaseNumber++;
            firstLine = in.nextLine();
            nTeams = Integer.parseInt(firstLine);
        }
    }

    private void processTestCase(int testCaseNumber){
        String command = in.nextLine();
        System.out.println("Scenario #" + testCaseNumber);
        while (!command.equals("STOP")) {
            if (command.substring(0, 7).equals("DEQUEUE")) {
                dequeue();
            }
            else {
                String[] com = command.split("\\s+");
                int element = Integer.parseInt(com[1]);
                enqueue(element);
            }
            command = in.nextLine();
        }
        System.out.print("\n");
    }


    public void enqueue(int x) { // Linear time on number of teams = constant time

        boolean added = false;

        try {
            queue.get(teams.get(x)).offer(x);
            added = true;
        } catch (NullPointerException e) {}

//        for (int key : queue.keySet()) {//Get every single queue checking if same team
//            if (key == teams.get(x)) {
//                added = true;
//                queue.get(key).offer(x);
//            }
//        } 10 ms more slow

        if (!added) { //Not found anyone belonging to his team, enqueue new Queue
            Queue<Integer> newQueue = new LinkedList<>();
            newQueue.offer(x);
            queue.put(teams.get(x),newQueue);
            q.offer(newQueue);
        }
    }

    public void dequeue() {
        if (!q.isEmpty()){
            int borrado = q.peek().remove();
            if (q.peek().isEmpty()) {
                q.remove();
                queue.remove(teams.get(borrado));//Removing and empty queue from hashmap queue
            }
            System.out.println(borrado);
        }
    }

    public static void main(String[] args) {
        in = new Scanner(System.in);
        new Main().run();
        in.close();
    }
}
