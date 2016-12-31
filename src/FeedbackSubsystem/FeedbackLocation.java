package FeedbackSubsystem;

import java.util.GregorianCalendar;

import LocationSubsystem.Location;
import UserSubsystem.RegisteredUser;
/**
*Classe che rappresenta un feedback ad un luogo. Estende la classe feedback.
*@param locationID = identificativo del luogo
*/
public class FeedbackLocation extends Feedback {

	private int locationID;

	public FeedbackLocation(int id, RegisteredUser sender, String message, GregorianCalendar date, int locationID) {
		super(id, sender, message, date);
		this.locationID = locationID;
	}



	public FeedbackLocation(RegisteredUser sender, String message, GregorianCalendar date, int locationID) {
		super(sender, message, date);
		this.locationID = locationID;
	}


	/**
	*Metodo che restituisce l'identificativo del luogo.
	*@return locationID
	*/
	public int getFeedbackOwner() {
		return this.locationID;
	}

}