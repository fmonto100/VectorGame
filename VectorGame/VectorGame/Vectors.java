package VectorGame;

import java.util.Scanner;

public class Vectors {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Board board = new Board();
		ComputerAI ai = new ComputerAI();
		HighScore recordBook = new HighScore();
		
		System.out.print("Enter number of rounds (Default 20): ");
		int totalRounds = 20;
		String roundInput = scanner.nextLine().trim();
		if (!roundInput.isEmpty()) {
			try {
				totalRounds = Integer.parseInt(roundInput);
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Defaulting to 20 rounds.");
			}
		}
		
		int humanScore = 0;
		int computerScore = 0;
		int humanRoundPoints = 0;
		int computerRoundPoints = 0;
		
		//Main Game Loop
		for(int round = 1; round <= totalRounds; round++) {
		System.out.println("\n*************************");
		System.out.println("- V E C T O R S                  *");
		System.out.println("*****************************");
		System.out.printf("-High Score: %-21s *\n", recordBook.getDisplayString());
		System.out.println("*****************************");
		System.out.printf("- Player Score: %2d Last Round %2d *\n", humanScore, humanRoundPoints);
		System.out.printf("- Computer Score: %2d Last Round %2d *\n", computerScore, computerRoundPoints);
		System.out.println("*****************************");
		System.out.printf("- Turn %d of %-22d *\n", round, totalRounds);
		System.out.println("*****************************");
		
		board.display();
			
			//My turn
			int row = -1, col = -1;
			while(true) {
				System.out.print("Player(X) Turn (Row Col): ");
				String input = scanner.nextLine().trim();
				String[] parts = input.split("\\s+");
				
				if (parts.length == 2) {
					try {
						row = Integer.parseInt(parts[0]);
						col = Integer.parseInt(parts[1]);
				
						if (board.isValidMove(row, col)) {
							board.placeToken(row, col, 'X');
							break;
						} else {
							System.out.println("Invalid move! Try again.");
						}
					} catch (NumberFormatException e) {
						System.out.println("Invalid format! Please enter number only.");
					}
				}else {
					System.out.println("Invalid input! Enter two numbers seperated by a space.");
				}
			}
			
			humanRoundPoints = board.calculateLastMoveScore('X');
			humanScore += humanRoundPoints;
			
			ai.makeMove(board);
			
			computerRoundPoints = board.calculateLastMoveScore('C');
			computerScore += computerRoundPoints;
			
		}
		
		System.out.println("\n============================");
		System.out.println("            GAME OVER          ");
		System.out.println("================================");
		board.display();
		System.out.printf("Final Score --> Player: %d | Computer: %d\n", humanScore, computerScore);
		
		if (humanScore > computerScore) {
			System.out.println("Congratulations! You win!");
			recordBook.checkAndSave(humanScore, scanner);
		} else if (computerScore > humanScore) {
			System.out.println("Computer wins! Better luck next time.");
			recordBook.checkAndSave(computerScore, scanner);
		} else {
			System.out.println("It's a tie game!");
		}
		
		scanner.close();
		
	}

}
