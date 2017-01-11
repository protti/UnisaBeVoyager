package FeedbackSubsystem;

import java.util.GregorianCalendar;

import UserSubsystem.RegisteredUser;
/**
*Classe che rappresenta un feedback.
*@param id = identificativo del feedback
*@param sender = string del mittente
*@param message = messaggio di feedback
*@param date = data del feedback
*/
public abstract class Feedback {

	private int id;
	private RegisteredUser sender;
	private String message;
	private String date;
	
	public Feedback(){
		id = 0;
	};
	
	public Feedback(RegisteredUser sender, String message, String date) {
		super();
		this.sender = sender;
		this.message = message;
		this.date = date;
	}
	
	public Feedback(int id, RegisteredUser sender, String message, String date) {
		super();
		this.id = id;
		this.sender = sender;
		this.message = message;
		this.date = date;
	}
	
	public void setId(int id){
		this.id = id;
	}
	/**
	*Metodo che restituisce l'identificativo del feedback.
	*@return id
	*/
	public int getId() {
		return id;
	}
	/**
	*Metodo che restituisce il mittente del feedback.
	*@return sender
	*/
	public RegisteredUser getSender() {
		return sender;
	}
	/**
	*Metodo che restituisce il messaggio del feedback.
	*@return message
	*/
	public String getMessage() {
		return message;
	}
	/**
	*Metodo che restituisce la data del feedback.
	*@return date
	*/
	public String getDate() {
		return date;
	}	
	
	public abstract int getFeedbackOwner();
		
}