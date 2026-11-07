package VectorGame;

public class Board {
	private char [] [] grid;
	private static final int SIZE = 8;
	
	private int lastRow = -1;
	private int lastCol = -1;
	
	public Board() {
		grid = new char[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				grid[i][j] = ' ';
			}
		}
	}
	
	public boolean isValidMove(int row, int col) {
		return row>= 0 && row < SIZE && col >= 0 && col < SIZE && grid[row][col] == ' ';
		
	}
	
	public void placeToken(int row, int col, char token) {
		if (isValidMove(row, col)) {
			grid[row][col] = token;
			
			this.lastRow = row;
			this.lastCol = col;
		}
	}
	
	public int calculateLastMoveScore(char token) {
		if (lastRow == -1 || lastCol == -1) return 0;
		
		int moveScore = 0;
		
		int[][] directions = {
				{0,1},
				{1,0},
				{1,1},
				{1,-1}
		};
		
		for (int[] dir : directions) {
			int dr = dir[0];
			int dc = dir[1];
			int count = 1;
			
			int r = lastRow + dr;
			int c = lastCol + dc;
			while(r >= 0 && r < SIZE && c >= 0 && c < SIZE && grid[r][c] == token) {
				count++;
				r += dr;
				c += dc;
			} 
			
			r = lastRow - dr;
			c = lastCol - dc;
			while (r >= 0 && r < SIZE && c >= 0 && c < SIZE && grid[r][c] == token) {
				count++;
				r-= dr;
				c-= dc;
			}
			moveScore += getPoints(count);
		}
		return moveScore;
		
		}
	
	
	public void display() {
		System.out.println("\n   0   1   2   3   4   5   6   7");
		System.out.println(" ---------------------------------");
		
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				System.out.print(" | " + grid[i][j]);
				
			}
			System.out.println(" | " + i);
			System.out.println(" ---------------------------------");
		}
	}
	
	public int calculateScore(char token) {
		int score = 0;
		//Horizontal Scan
		for (int i = 0; i < SIZE; i++) {
			int count = 0;
			for (int j = 0; j < SIZE; j++) {
				if (grid[i][j] == token) {
					count++;
				} else {
					score += getPoints(count);
					count = 0;
				}
			}
			score += getPoints(count);
		}
		//Vertical Scan
		for (int j = 0; j < SIZE; j++) {
			int count = 0;
			for (int i = 0; i < SIZE; i++) {
				if (grid[i][j] == token) {
					count++;
				} else {
					score += getPoints(count);
					count = 0;
				}
			}
			score += getPoints(count);
		}
		//Diagonal Scan
		for (int k = -(SIZE - 1); k < SIZE; k++) {
			int count = 0;
			for (int i = 0; i < SIZE; i++) {
				int j = i - k;
				if(j >= 0 && j < SIZE) {
					if(grid[i][j] == token) {
						count++;
					} else {
						score += getPoints(count);
						count = 0;
					}
				}
			}
			score += getPoints(count);
		}
			
		//Diagonal Scan (Down-Left to Up-Right)
		for (int k = 0; k <= 2 * (SIZE - 1); k++) {
			int count = 0;
			for (int i = 0; i < SIZE; i++) {
				int j = k - i;
				if (j >= 0 && j < SIZE) {
					if (grid[i][j] == token) {
						count++;
					} else {
						score += getPoints(count);
						count = 0;
					}
				}
			}
			score += getPoints(count);
		}
			
		return score;
		
	}
	private int getPoints(int length) {
		if(length == 3) return 4;
		if(length == 4) return 6;
		if(length == 5) return 10;
		return 0;
	}
}
		
		
		
		
		
		

