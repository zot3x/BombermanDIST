package FireBase;

public class FirebaseUser {
	
	public int Highscore;
	public String Navn;
	public String studieNummer;
	

	public FirebaseUser(String studieNummer, String Navn, int HighScore ){
		
		this.Highscore = HighScore;
		this.Navn = Navn;
		this.studieNummer = studieNummer;
	}
	
	public FirebaseUser(){
		
	}
}
