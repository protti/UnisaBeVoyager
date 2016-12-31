package FeedbackSubsystem;

import java.util.GregorianCalendar;

import LocationSubsystem.Location;
/**
*Classe che rappresenta un feedback ad un luogo. Estende la classe feedback.
*@param locationID = identificativo del luogo
*/
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


	/**
	*Metodo che restituisce l'identificativo del luogo.
	*@return locationID
	*/
	public int getFeedbackOwner() {
		return this.locationID;
	}

}