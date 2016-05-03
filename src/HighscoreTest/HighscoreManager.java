package HighscoreTest;

import java.util.*;
import java.io.*;


public class HighscoreManager {

	private ArrayList<Score> scores;
	
	private static final String HIGHSCOREFIL = "highscore.txt";

	//Initialising an in and outputStream for working with the file
	ObjectOutputStream outputStream = null;
	ObjectInputStream inputStream = null;

	public HighscoreManager() {
		//initialising the scores-arraylist
		scores = new ArrayList<Score>();
	}

	public ArrayList<Score> getScores() {
		loadScoreFile();
		sort();
		return scores;
	}

	private void sort() {
		ScoreSammenligning comparator = new ScoreSammenligning();
		Collections.sort(scores, comparator);
	}

	public void addScore(String navn, int score) {
		loadScoreFile();
		scores.add(new Score(navn, score));
		updateScoreFile();
	}

	public void loadScoreFile() {
		try {
			inputStream = new ObjectInputStream(new FileInputStream(HIGHSCOREFIL));
			scores = (ArrayList<Score>) inputStream.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("[Laad] Filen findes ikke: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("[Laad] IO fejl: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("[Laad] Klassen er ikke fundet: " + e.getMessage());
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
				System.out.println("[Laad] IO fejl: " + e.getMessage());
			}
		}
	}
	
	public void updateScoreFile() {
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCOREFIL));
            outputStream.writeObject(scores);
        } catch (FileNotFoundException e) {
            System.out.println("[Update] Filen er ikke fundet: " + e.getMessage() + ", der laves en ny fil");
        } catch (IOException e) {
            System.out.println("[Update] IO fejl: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();

                }
            } catch (IOException e) {
                System.out.println("[Update] fejl: " + e.getMessage());
            }
        }
}
	
	public String getHighscoreString() {
        String highscoreString = "";
	    int max = 10;

        ArrayList<Score> scores;
        scores = getScores();

        int i = 0;
        int x = scores.size();
        if (x > max) {
            x = max;
        }
        while (i < x) {
            highscoreString += (i + 1) + ".\t" + scores.get(i).getNavn() + "\t\t" + scores.get(i).getScore() + "\n";
            i++;
        }
        return highscoreString;
}


}
