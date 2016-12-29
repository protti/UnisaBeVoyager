package FeedbackSubsystem;

public class Feedback {

	private int id;
	private String sender;
	private String message;
	private int date;
	
		public Feedback(int id, String sender, String message, int date) {
		super();
		this.id = id;
		this.sender = sender;
		this.message = message;
		this.date = date;
	}
	
	public int getId() {
		return id;
	}
	public String getSender() {
		return sender;
	}
	public String getMessage() {
		return message;
	}
	public int getDate() {
		return date;
	}	
	
}