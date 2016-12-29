package FeedbackSubsystem;

import java.util.GregorianCalendar;

public class FeedbackRoute extends Feedback {

	private int routeID;

	public FeedbackRoute(int id, String sender, String message, GregorianCalendar date, int routeID) {
		super(id, sender, message, date);
		this.routeID = routeID;
	}
	
	public int getRouteID() {
		return this.routeID;
	}

}