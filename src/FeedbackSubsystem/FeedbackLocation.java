package FeedbackSubsystem;

import java.util.GregorianCalendar;

import LocationSubsystem.Location;

public class FeedbackLocation extends Feedback {

	private int locationID;

	public FeedbackLocation(int id, String sender, String message, GregorianCalendar date, int locationID) {
		super(id, sender, message, date);
		this.locationID = locationID;
	}



	public FeedbackLocation(String sender, String message, GregorianCalendar date, int locationID) {
		super(sender, message, date);
		this.locationID = locationID;
	}



	public int getLocationID() {
		return this.locationID;
	}

}