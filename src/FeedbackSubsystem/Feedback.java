package FeedbackSubsystem;

import java.util.GregorianCalendar;

public class Feedback {

	private int id;
	private String sender;
	private String message;
	private GregorianCalendar date;
	
		public Feedback(int id, String sender, String message, GregorianCalendar date) {
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
	public GregorianCalendar getDate() {
		return date;
	}	
	
}