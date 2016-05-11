package FireBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FirebaseListener {
	
	public void top10(ArrayList<FirebaseUser> scoreList) {
		
		Collections.sort((List<FirebaseUser>)scoreList, new Comparator<FirebaseUser>() {

			@Override
			public int compare(FirebaseUser o1, FirebaseUser o2) {
				if (o1.Highscore < o2.Highscore){
					return 1;					
				}else if (o1.Highscore > o2.Highscore){
					return -1;					
				}
				return 0;
			}
			
		});
		
	}
	
}