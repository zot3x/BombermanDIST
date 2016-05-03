package FireBase;

public class FirebaseController {

	private HighScoreController hSC;


	public FirebaseController(){

		hSC = new HighScoreController();
	}
	public HighScoreController Highscore(){

		return hSC;
	}
}
