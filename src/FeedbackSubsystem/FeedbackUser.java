package FeedbackSubsystem;

public class FeedbackUser extends Feedback {

	

	private int userID;

	public FeedbackUser(int id, String sender, String message, int date, int userID) {
		super(id, sender, message, date);
		this.userID = userID;
	}
	
	public int getRecipientID() {
		return this.userID;
	}

}