package FireBase;

import java.lang.reflect.Array;
import java.util.ArrayList;

import FireBase.FirebaseListener;

public class Main {

	public static void main(String[] args) throws FirebaseDataException{
		
//		FirebaseController fbC = new FirebaseController();
//		FirebaseListener fbl = new FirebaseListener();
//		UserController uDAO = new UserController();
//	
		HighScoreController hSC = new HighScoreController();
////					uDAO.saveUser(new FirebaseUser("test6", "", 0));
////					uDAO.saveUser(new FirebaseUser("test7", "", 0));
////					uDAO.saveUser(new FirebaseUser("test8", "", 0));
////					uDAO.saveUser(new FirebaseUser("test9", "", 0));
////					uDAO.saveUser(new FirebaseUser("test10", "", 0));
//					
//
		FirebaseListener fbl = new FirebaseListener()
		{

			@Override
			public void top10(ArrayList<FirebaseUser> scoreList) {
				String nameList = "asd";
//				for(int i = 0 ; i<scoreList.size() ; i++){
//					System.out.println(scoreList.get(i).Navn);
//				}
//				System.out.println(">>>" + scoreList.get(2).Navn + "  " +  scoreList.get(2).Highscore);
				for(int i = 0 ; i<scoreList.size() ; i++){
					nameList = scoreList.get(i).Navn.toString();
					System.out.println(nameList);
				}
			}
		};
		try {
			hSC.getTop10Score(fbl);
		} catch (FirebaseDataException e) {
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//	
//		System.out.println("klar");
//		ArrayList<FirebaseUser> list = new ArrayList<FirebaseUser>();
//		FirebaseListener fbl = new FirebaseListener();
//		fbl.top10(list);
//		String name = "";
//		System.out.println("klarrrrr");
////		for(int i = 0 ; i<=list.size() ; i++){
//		name = list.get(0).Navn.toString();
//		System.out.println("><<" + name);
////		}
//		try {
//			hSC.getTop10Score(fbl);
//		} catch (FirebaseDataException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
		System.out.println(hSC.getUserList());
		}
		
	}
