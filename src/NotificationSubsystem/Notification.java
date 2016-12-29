package NotificationSubsystem;

import UserSubsystem.RegisteredUser;

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
	
	public int getId() {
		return this.id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	public RegisteredUser getSender() {
		return this.sender;
	}

	/**
	 * 
	 * @param sender
	 */
	public void setSender(RegisteredUser sender) {
		this.sender = sender;
	}

	public RegisteredUser getRecipient() {
		return this.recipient;
	}

	/**
	 * 
	 * @param recipient
	 */
	public void setRecipient(RegisteredUser recipient) {
		this.recipient = recipient;
	}

	public String getBody() {
		return this.body;
	}

	/**
	 * 
	 * @param body
	 */
	public void setBody(String body) {
		this.body = body;
	}

	public boolean getReadType() {
		return this.read;
	}

	/**
	 * 
	 * @param readType
	 */
	public void setReadType(boolean readType) {
		this.read = read;
	}

	public String getSendDate() {
		return this.sendDate;
	}

	/**
	 * 
	 * @param sendDate
	 */
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

}