package FeedbackSubsystem;

import LocationSubsystem.Location;

public class FeedbackLocation extends Feedback {

	private int locationID;

	
	
	public FeedbackLocation(int id, String sender, String message, int date, int locationID) {
		super(id, sender, message, date);
		this.locationID = locationID;
	}



	public int getLocationID() {
		return this.locationID;
	}

}