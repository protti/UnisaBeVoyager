package NotificationSubsystem;

import UserSubsystem.RegisteredUser;
/**
*Classe che rappresenta una notifica.
*@param id = identificativo della notifica
*@param sender = utente registrato che invia la notifica
*@param recipient = utente registrato che riceve la notifica
*@param body = descrizione della notifica
*@param read = segnala se la notifica è letta o meno
*@param sendDate = data di invio della notifica
*/
public class Notification {

	private int id;
	private RegisteredUser sender;
	private RegisteredUser recipient;
	private String body;
	private boolean read;
	private String sendDate;

	
	public Notification(int id, RegisteredUser sender, RegisteredUser recipient, String body, boolean read,
			String sendDate) {
		this.id = id;
		this.sender = sender;
		this.recipient = recipient;
		this.body = body;
		this.read = read;
		this.sendDate = sendDate;
	}

	public Notification(RegisteredUser sender, RegisteredUser recipient, String body, boolean read, String sendDate) {
		this.sender = sender;
		this.recipient = recipient;
		this.body = body;
		this.read = read;
		this.sendDate = sendDate;
	}
	/**
	*Metodo che restituisce l'identificativo della notifica.
	*@return id
	*
	*/
	public int getId() {
		return this.id;
	}

	/**
	*Metodo che setta l'identificativo della notifica.
	*@param id
	*
	*/
	public void setId(int id) {
		this.id = id;
	}
	/**
	*Metodo che restituisce l'utente che invia la notifica.
	*@return sender
	*
	*/
	public RegisteredUser getSender() {
		return this.sender;
	}

	/**
	*Metodo che setta l'utente che invia la notifica.
	*@param sender
	*
	*/
	public void setSender(RegisteredUser sender) {
		this.sender = sender;
	}
	/**
	*Metodo che restituisce l'utente che riceve la notifica.
	*@return recipient
	*
	*/
	public RegisteredUser getRecipient() {
		return this.recipient;
	}

	/**
	*Metodo che restituisce l'utente che riceve la notifica.
	*@return recipient
	*
	*/
	public void setRecipient(RegisteredUser recipient) {
		this.recipient = recipient;
	}
	/**
	*Metodo che restituisce il corpo della notifica.
	*@return body
	*
	*/
	public String getBody() {
		return this.body;
	}

	/**
	*Metodo che setta il corpo della notifica.
	*@param body
	*
	*/
	public void setBody(String body) {
		this.body = body;
	}
	/**
	*Metodo che restituisce se la notifica è stata letta o meno.
	*@return read
	*
	*/
	public boolean getReadType() {
		return this.read;
	}

	/**
	*Metodo che setta la notifica come letta o non.
	*@param read
	*
	*/
	public void setReadType(boolean readType) {
		this.read = read;
	}
	/**
	*Metodo che restituisce la data di invio.
	*@return sendDate
	*
	*/
	public String getSendDate() {
		return this.sendDate;
	}

	/**
	*Metodo che setta la data di invio.
	*@param sendDate
	*
	*/
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

}