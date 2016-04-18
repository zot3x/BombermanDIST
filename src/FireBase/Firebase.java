package FireBase;


import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class Firebase {

	private com.firebase.client.Firebase fireBase;

	public Firebase() {
		readFirebase();
	}

	public void readFirebase() {

		fireBase = new com.firebase.client.Firebase("https://bombermandist.firebaseio.com");

		fireBase.addValueEventListener(new ValueEventListener() {

			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				String dataCondition = (String) dataSnapshot.getValue();
			}

			@Override
			public void onCancelled(FirebaseError firebaseError) {

			}
		});
	}
}