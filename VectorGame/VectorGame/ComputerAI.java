package VectorGame;

import java.util.Random;

public class ComputerAI {
	private Random rand = new Random();
	
	public void makeMove(Board board) {
		int row, col;
		do {
			row = rand.nextInt(8);
			col = rand.nextInt(8);
		} while (!board.isValidMove(row, col));
		
		board.placeToken(row, col, 'C');
		System.out.println("Computer placed 'C' at: " + row + " " + col);
	}

}
