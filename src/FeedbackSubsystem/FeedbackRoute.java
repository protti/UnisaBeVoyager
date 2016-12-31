package FeedbackSubsystem;

import java.util.GregorianCalendar;

import UserSubsystem.RegisteredUser;
/**
*Classe che rappresenta un feedback all'itinerario. Estende la classe feedback.
*@param routeID = identificativo del luogo
*/
public class FeedbackRoute extends Feedback {

	private int routeID;

	public FeedbackRoute(int id, RegisteredUser sender, String message, GregorianCalendar date, int routeID) {
		super(id, sender, message, date);
		this.routeID = routeID;
	}
	
	public FeedbackRoute(RegisteredUser sender, String message, GregorianCalendar date, int routeID) {
		super(sender, message, date);
		this.routeID = routeID; 
	}
	/**
	*Metodo che restituisce l'identificativo dell'itinerario.
	*@return routeID
	*/
	public int getFeedbackOwner() {
		return this.routeID;
	}

}