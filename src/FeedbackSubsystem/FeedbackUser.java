package FeedbackSubsystem;

import java.util.GregorianCalendar;

import UserSubsystem.RegisteredUser;
/**
*Classe che rappresenta un feedback ad un utente. Estende la classe feedback.
*@param userID = identificativo dell'utente
*/
public class FeedbackUser extends Feedback {

	private RegisteredUser userID;

	public FeedbackUser(RegisteredUser sender, String message, String date, RegisteredUser userID) {
		super(sender, message, date);
		this.userID = userID;
	}

	public FeedbackUser(int id, RegisteredUser sender, String message, String date, RegisteredUser userID) {
		super(id, sender, message, date);
		this.userID = userID;
	}
	/**
	*Metodo che restituisce l'identificativo dell'utente.
	*@return userID
	*/
	public int getFeedbackOwner() {
		return this.userID.getId();
	}

}