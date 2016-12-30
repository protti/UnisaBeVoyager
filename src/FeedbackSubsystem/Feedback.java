package FeedbackSubsystem;

import java.util.GregorianCalendar;
/**
*Classe che rappresenta un feedback.
*@param id = identificativo del feedback
*@param sender = string del mittente
*@param message = messaggio di feedback
*@param date = data del feedback
*/
public class Feedback {

	private int id;
	private String sender;

	private String message;
	private GregorianCalendar date;
	
	public Feedback(String sender, String message, GregorianCalendar date) {
		super();
		this.sender = sender;
		this.message = message;
		this.date = date;
	}
	
	public Feedback(int id, String sender, String message, GregorianCalendar date) {
		super();
		this.id = id;
		this.sender = sender;
		this.message = message;
		this.date = date;
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
	public String getSender() {
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
	public GregorianCalendar getDate() {
		return date;
	}	
	
}