package HighscoreTest;


import java.io.Serializable;

public class Score  implements Serializable {
    private int score;
    private String navn;

    public int getScore() {
        return score;
    }

    public String getNavn() {
        return navn;
    }

    public Score(String navn, int score) {
        this.score = score;
        this.navn = navn;
    }
}
