package FireBase;

import java.util.ArrayList;

import org.w3c.dom.NameList;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import FireBase.FirebaseListener;

public class HighScoreController {

	private Firebase fb = new Firebase(FirebaseConnection.URL);
	private FirebaseListener fbL = new FirebaseListener();
	private ArrayList<FirebaseUser> userList = new ArrayList<FirebaseUser>();
	
	public HighScoreController(){

	}

	public void getTop10Score(FirebaseListener aListener) throws FirebaseDataException{


		Runnable run = new Runnable() {
			@Override
			public void run() {

				userList.clear();
				
//				ArrayList<FirebaseUser> scoreList = new ArrayList<FirebaseUser>();

				Query ref = fb.child("Users");

				ref.addValueEventListener(new ValueEventListener() {

					@Override
					public void onDataChange(DataSnapshot dataSnapshot) {

						for(DataSnapshot d : dataSnapshot.getChildren()){
							userList.add(d.getValue(FirebaseUser.class));
						}
//						System.out.println(dataSnapshot.getChildrenCount());
						aListener.top10(userList);
					}

					@Override
					public void onCancelled(FirebaseError arg0) {
	
					}
				});

			}
		};
		run.run();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
		}
	}

	public ArrayList<FirebaseUser> getUserList(){
		
		
		return userList;
	}
	

//	public void updateUserList(ArrayList<FirebaseUser> updateUser){	
//		updateUser = getUserList();
//	}
}
