package FireBase;

import java.util.ArrayList;

import FireBase.FirebaseListener;

public class Main {

	public static void main(String[] args) {

		UserController uDAO = new UserController();
//
		HighScoreController hSC = new HighScoreController();
					uDAO.saveUser(new FirebaseUser("test6", "", 0));
					uDAO.saveUser(new FirebaseUser("test7", "", 0));
					uDAO.saveUser(new FirebaseUser("test8", "", 0));
					uDAO.saveUser(new FirebaseUser("test9", "", 0));
					uDAO.saveUser(new FirebaseUser("test10", "", 0));
					

//		FirebaseListener fbl = new FirebaseListener()
//		{
//
//			@Override
//			public void top10(ArrayList<FirebaseUser> scoreList) {
//				System.out.println(">>>" + scoreList.get(2).Navn + "  " +  scoreList.get(2).Highscore);
//			}
//		};
//		try {
//			hSC.getTop10Score(fbl);
//		} catch (FirebaseDataException e) {
//		}
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
