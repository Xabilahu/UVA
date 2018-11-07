package EcologicalBinPacking_102;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    static Scanner in;

    private void run() {

        //No need to check alphabetical order in the end because its being checked in ascending alphabetical order

        int[] bottles = new int[9];
        while (in.hasNext()) {
            //Input processing
            for (int i = 0; i < 9; i++) bottles[i] = in.nextInt();

            int minMoves = Integer.MIN_VALUE, actualMoves = 0;
            String winnerBinConfig = "";

            //Index : B G C   B G C   B G C
            //        0 1 2   3 4 5   6 7 8

            //GCB
            actualMoves += bottles[4] + bottles[7];//G Moves
            actualMoves += bottles[2] + bottles[8];//C Moves
            actualMoves += bottles[0] + bottles[3];//B Moves
            minMoves = actualMoves;
            winnerBinConfig = "GCB " + minMoves;

            //GBC
            actualMoves = 0;
            actualMoves += bottles[4] + bottles[7];//G Moves
            actualMoves += bottles[0] + bottles[6];//B Moves
            actualMoves += bottles[2] + bottles[5];//C Moves
            if (actualMoves <= minMoves) {
                minMoves = actualMoves;
                winnerBinConfig = "GBC " + minMoves;
            }

            //CGB
            actualMoves = 0;
            actualMoves += bottles[5] + bottles[8];//C Moves
            actualMoves += bottles[1] + bottles[7];//G Moves
            actualMoves += bottles[0] + bottles[3];//B Moves
            if (actualMoves <= minMoves) {
                minMoves = actualMoves;
                winnerBinConfig = "CGB " + minMoves;
            }

            //CBG
            actualMoves = 0;
            actualMoves += bottles[5] + bottles[8];//C Moves
            actualMoves += bottles[0] + bottles[6];//B Moves
            actualMoves += bottles[1] + bottles[4];//G Moves
            if (actualMoves <= minMoves) {
                minMoves = actualMoves;
                winnerBinConfig = "CBG " + minMoves;
            }

            //BGC
            actualMoves = 0;
            actualMoves += bottles[3] + bottles[6];//B Moves
            actualMoves += bottles[1] + bottles[7];//G Moves
            actualMoves += bottles[2] + bottles[5];//C Moves
            if (actualMoves <= minMoves) {
                minMoves = actualMoves;
                winnerBinConfig = "BGC " + minMoves;
            }

            //BCG
            actualMoves = 0;
            actualMoves += bottles[3] + bottles[6];//B Moves
            actualMoves += bottles[2] + bottles[8];//C Moves
            actualMoves += bottles[1] + bottles[4];//G Moves
            if (actualMoves <= minMoves) {
                minMoves = actualMoves;
                winnerBinConfig = "BCG " + minMoves;
            }

            System.out.println(winnerBinConfig);
        }

    }

    public static void main(String[] args) throws IOException {
        in = new Scanner(System.in);
        new Main().run();
        in.close();
    }

}
