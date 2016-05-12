package FireBase;

import java.lang.reflect.Array;
import java.util.ArrayList;

import FireBase.FirebaseListener;

public class Main {

	public static void main(String[] args) throws FirebaseDataException{
		
		FirebaseController fbC = new FirebaseController();
		FirebaseListener fbl = new FirebaseListener();
		UserController uDAO = new UserController();
	
		HighScoreController hSC = new HighScoreController();
					uDAO.saveUser(new FirebaseUser("Hadi600", "HadiWow", 80000));
//					uDAO.saveUser(new FirebaseUser("Joe11", "Joee12", 12));
//					uDAO.saveUser(new FirebaseUser("lel", "leeel992", 111));
//					uDAO.saveUser(new FirebaseUser("mums", "meeen91", 344));
//					uDAO.saveUser(new FirebaseUser("test10", "rød03", 33));
//					
//
//		FirebaseListener fbl = new FirebaseListener()
//		{
//
//			@Override
//			public void top10(ArrayList<FirebaseUser> scoreList) {
//				String nameList = "asd";
////				for(int i = 0 ; i<scoreList.size() ; i++){
////					System.out.println(scoreList.get(i).Navn);
////				}
////				System.out.println(">>>" + scoreList.get(2).Navn + "  " +  scoreList.get(2).Highscore);
//				for(int i = 0 ; i<scoreList.size() ; i++){
//					nameList = scoreList.get(i).Navn.toString();
//					System.out.println(nameList);
//				}
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
//		
		
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
