package FeedbackSubsystem;

import java.util.GregorianCalendar;

public class FeedbackUser extends Feedback {

	

	private int userID;

	public FeedbackUser(int id, String sender, String message, GregorianCalendar date, int userID) {
		super(id, sender, message, date);
		this.userID = userID;
	}
	
	public int getRecipientID() {
		return this.userID;
	}

}