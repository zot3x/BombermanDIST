package FireBase;

import com.firebase.*;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

public class UserDAO {

	private com.firebase.client.Firebase fb;
	private Query que;

	public UserDAO(String user){

		fb = new com.firebase.client.Firebase("https://bombermandist.firebaseio.com/users");
	}

	public void saveUser(final UserDTO) throws FirebaseException
	{
		Runnable runThis = new Runnable() {
			@Override
			public void run() {

				Query que = fb;

				que.addListenerForSingleValueEvent(new ValueEventListener(){

					@Override
					public void onDataChange(DataSnapshot dataSnapshot) {
						int count = (int)(dataSnapshot.getChildrenCount() + 1);

					}

					@Override
					public void onCancelled(FirebaseError firebaseError) {
						throw new FirebaseDataException("Did not recieve any data");
					}

				});
			}
		};

	}
}
