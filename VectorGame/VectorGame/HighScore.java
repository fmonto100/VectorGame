package VectorGame;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class HighScore {
	private static final String FILE_NAME = "highscore.txt";
	private String holderName;
	private int score;
	
	public HighScore() {
		this.holderName = "None";
		this.score = 0;
		load();
	}
	
	public String getHolderName() {
		return holderName;
		
	}
	
	public int getScore() {
		return score;
	}
	
	public void load() {
		File file = new File(FILE_NAME);
		if (!file.exists()) return;
		
		try (Scanner fileScanner = new Scanner(file)) {
			if (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String[] parts = line.split(" - ");
				if (parts.length == 2) {
					this.holderName = parts[0].trim();
					this.score = Integer.parseInt(parts[1].trim());
				}
			}
		} catch (Exception e) {
			this.holderName = "None";
			this.score = 0;
		}
	}
					
	public void checkAndSave(int finalScore, Scanner scanner) {
		if (finalScore > this.score) {
			this.score = finalScore;
			
			System.out.print("\n[NEW RECORD!] Enter you name for the leadershipboard; ");
			String newName = scanner.nextLine(). trim();
			
			if(newName.isEmpty()) {
				newName = "Player";
			}
			this.holderName = newName;
			
			try (FileWriter writer = new FileWriter(FILE_NAME, false)) {
				writer.write(holderName + " - " + score);
				System.out.println("\n[System] NEW high score recorded in highscore.txt!");
			} catch (IOException e) {
				System.out.println("\n[Error] Could not save high score to file. " + e.getMessage());
			}
		} else {
			System.out.println("\n[System] High score not beaten. Current record remains: " + this.score);
		}
	}
	
	public String getDisplayString() {
		return holderName + " - " + score;
	}
}

