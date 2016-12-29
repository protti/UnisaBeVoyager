package TravelSubsystem;


import UserSubsystem.*;
import java.util.*;

import PollSubsystem.Poll;
import RouteSubsystem.Route;

public class Travel {

	private RegisteredUser creatoreViaggio;
	Collection<RegisteredUser> partecipantiViaggio;
	Collection<Poll> pollList;
	Route route;
	private int id;
	private String startDate;
	private String endDate;
	private boolean type;

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

	public String getStartDate() {
		return this.startDate;
	}

	/**
	 * 
	 * @param startDate
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return this.endDate;
	}

	/**
	 * 
	 * @param endDate
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public boolean getType() {
		return this.type;
	}

	/**
	 * 
	 * @param type
	 */
	public void setType(boolean type) {
		this.type = type;
	}

}