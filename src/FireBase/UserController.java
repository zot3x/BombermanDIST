package FireBase;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.FirebaseException;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

public class UserController {

	private Firebase fb;
	public UserController(){

		fb = new Firebase(FirebaseConnection.URL);
	}

	public void saveUser(FirebaseUser fbU) throws FirebaseException

	{
		Runnable run = new Runnable() {
			@Override
			public void run() {

				System.out.println(">>>> execute <<<<");

				Query que = fb;

				que.addListenerForSingleValueEvent(new ValueEventListener(){

					@Override
					public void onDataChange(DataSnapshot dataSnapshot) {
						Firebase ref = fb.child("Users").child(fbU.studieNummer);
						ref.setValue(fbU);
//						System.out.println("Gemt i DB >>>>>>> " + dataSnapshot.getChildrenCount());
						System.out.println("Gemt i DB >>>>>>>");
					}

					@Override
					public void onCancelled(FirebaseError firebaseError) {
						try {
							throw new FirebaseDataException("Did not recieve any data");
						} catch (FirebaseDataException e) {
							System.out.println("Feeeejl ->" + e.getMessage());
						}
					}

				});
			}
		};
		run.run();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
	}
}
