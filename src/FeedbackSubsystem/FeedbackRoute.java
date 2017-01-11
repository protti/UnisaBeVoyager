package FeedbackSubsystem;

import java.util.GregorianCalendar;

import RouteSubsystem.Route;
import UserSubsystem.RegisteredUser;
/**
*Classe che rappresenta un feedback all'itinerario. Estende la classe feedback.
*@param routeID = identificativo del luogo
*/
public class FeedbackRoute extends Feedback {

	private Route route;

	public FeedbackRoute(){};
	
	public FeedbackRoute(int id, RegisteredUser sender, String message, String date, Route route) {
		super(id, sender, message, date);
		this.route = route;
	}
	
	public FeedbackRoute(RegisteredUser sender, String message, String date, Route route) {
		super(sender, message, date);
		this.route = route; 
	}
	/**
	*Metodo che restituisce l'identificativo dell'itinerario.
	*@return routeID
	*/
	public int getFeedbackOwner() {
		return this.route.getId();
	}

}