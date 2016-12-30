package PollSubsystem;

import java.util.GregorianCalendar;

public class Poll {

	private int id;
	private String description;
	private int vnegative;
	private int vpositive;
	private GregorianCalendar startDate;
	private GregorianCalendar endDate;

	public GregorianCalendar getEndDate() {
		return this.endDate;
	}

	
	
	public Poll(int id, String description, int vnegative, int vpositive, GregorianCalendar startDate, GregorianCalendar endDate) {
		super();
		this.id = id;
		this.description = description;
		this.vnegative = vnegative;
		this.vpositive = vpositive;
		this.startDate = startDate;
		this.endDate = endDate;
	}


	public Poll(String description, int vnegative, int vpositive, GregorianCalendar startDate,
			GregorianCalendar endDate) {
		super();
		this.description = description;
		this.vnegative = vnegative;
		this.vpositive = vpositive;
		this.startDate = startDate;
		this.endDate = endDate;
	}



	/**
	 * 
	 * @param endDate
	 */
	public void setEndDate(GregorianCalendar endDate) {
		this.endDate = endDate;
	}

	public GregorianCalendar getStartDate() {
		return this.startDate;
	}

	/**
	 * 
	 * @param startDate
	 */
	public void setStartDate(GregorianCalendar startDate) {
		this.startDate = startDate;
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

	public String getDescription() {
		return this.description;
	}

	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public int getVnegative() {
		return this.vnegative;
	}

	/**
	 * 
	 * @param vnegative
	 */
	public void setVnegative(int vnegative) {
		this.vnegative = vnegative;
	}

	public int getVpositive() {
		return this.vpositive;
	}

	/**
	 * 
	 * @param vpositive
	 */
	public void setVpositive(int vpositive) {
		this.vpositive = vpositive;
	}

}