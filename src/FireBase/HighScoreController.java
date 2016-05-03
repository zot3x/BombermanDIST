package FireBase;

import java.util.ArrayList;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import FireBase.FirebaseListener;

public class HighScoreController {

	Firebase fb = new Firebase(FirebaseConnection.URL);

	public HighScoreController(){

	}

	public void getTop10Score(FirebaseListener aListener) throws FirebaseDataException{


		Runnable run = new Runnable() {
			@Override
			public void run() {


				ArrayList<FirebaseUser> scoreList = new ArrayList<FirebaseUser>();

				Query ref = fb.child("Users");

				ref.addValueEventListener(new ValueEventListener() {

					@Override
					public void onDataChange(DataSnapshot dataSnapshot) {

						for(DataSnapshot d : dataSnapshot.getChildren()){
							scoreList.add(d.getValue(FirebaseUser.class));
						}
						System.out.println(dataSnapshot.getChildrenCount());
						aListener.top10(scoreList);
					}

					@Override
					public void onCancelled(FirebaseError arg0) {
	
					}
				});

			}
		};
		run.run();
	}

	public int getSingleScore(){



		return 0;
	}

	public void updateHighscore(){

	}
}
