package KnightMoves_439;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static Scanner in;
    final int ROWS = 8;
    final int COLUMNS = 8;

    public void run() {
        while (in.hasNext()){
        	String s = in.nextLine();
        	String[] line = s.split(" ");
        	int result = this.moves(this.convertFromString(line[0]), this.convertFromString(line[1]));
        	System.out.println("To get from " + line[0] + " to " + line[1] + " takes " + result + " knight moves.");
        }
    }
    
	private int moves(Cell c1, Cell c2) { 
        Cell start = c1, end = c2;
        Queue<Cell> cellsToCheck = new LinkedList<>();
        boolean[][] checkedCells = new boolean[this.ROWS][this.COLUMNS];
        int[][] movs = new int[this.ROWS][this.COLUMNS];
        boolean found = false;

        for (int i = 0; i < movs.length; i++) 
            for (int j = 0; j < movs[0].length; j++)
                movs[i][j] = 0;

        cellsToCheck.offer(start);
        checkedCells[start.x][start.y] = true;

        while (!cellsToCheck.isEmpty() && !found) {
            Cell actualCell = cellsToCheck.remove();
            if (actualCell.equals(end)) found = true;
            else {
                this.processCoordinate(actualCell.x + 1, actualCell.y + 2, cellsToCheck, actualCell, checkedCells, movs);
                this.processCoordinate(actualCell.x - 1, actualCell.y + 2, cellsToCheck, actualCell, checkedCells, movs);
                this.processCoordinate(actualCell.x + 1, actualCell.y - 2, cellsToCheck, actualCell, checkedCells, movs);
                this.processCoordinate(actualCell.x - 1, actualCell.y - 2, cellsToCheck, actualCell, checkedCells, movs);
                this.processCoordinate(actualCell.x + 2, actualCell.y + 1, cellsToCheck, actualCell, checkedCells, movs);
                this.processCoordinate(actualCell.x - 2, actualCell.y + 1, cellsToCheck, actualCell, checkedCells, movs);
                this.processCoordinate(actualCell.x + 2, actualCell.y - 1, cellsToCheck, actualCell, checkedCells, movs);
                this.processCoordinate(actualCell.x - 2, actualCell.y - 1, cellsToCheck, actualCell, checkedCells, movs);
            }
        }

		return movs[end.x][end.y];
	}
    
	private void processCoordinate(int x, int y, Queue<Cell> cellsToCheck, Cell actual, boolean[][] checkedCells, int[][] movs){
		Cell nueva;
		if ((x >= 0) && (x < this.ROWS) && (y >= 0) && (y < this.COLUMNS) && (!checkedCells[x][y])) {
			nueva = new Cell(x, y);
			cellsToCheck.add(nueva);
			checkedCells[x][y] = true;
			movs[x][y] = movs[actual.x][actual.y] + 1;
		}
	}
	
    private Cell convertFromString(String s){ // For example: "b5" --> (1, 4)
    	Character c1 = s.charAt(0); 
    	int row = ((int) c1) - ((int) 'a');

    	Character c2 = s.charAt(1); 
    	int col = Integer.parseUnsignedInt(c2.toString()) - 1;
    	
    	return new Cell(row, col);
    }
	
	
	public static void main(String[] args) {
        in = new Scanner(System.in);
        new Main().run();
        in.close();
	}

	
	private class Cell {

		int x;
		int y;
		
		public Cell(int px, int py){
			this.x = px;
			this.y = py;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Cell other = (Cell) obj;
			if (this.x != other.x)
				return false;
			if (this.y != other.y)
				return false;
			return true;
		}		
	}
}