package TravelSubsystem;


import UserSubsystem.*;
import java.util.*;

import PollSubsystem.Poll;
import RouteSubsystem.Route;

public class Travel {

	private RegisteredUser creatoreViaggio;
	private Collection<RegisteredUser> partecipantiViaggio;
	private Collection<Poll> pollList;
	private Route route;
	private int id;
	private String startDate;
	private String endDate;
	public Travel(RegisteredUser creatoreViaggio, int id, String startDate, String endDate, boolean type) {

		this.creatoreViaggio = creatoreViaggio;
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.type = type;
	}

	public Travel(RegisteredUser creatoreViaggio, String startDate, String endDate, boolean type) {
		this.creatoreViaggio = creatoreViaggio;
		this.startDate = startDate;
		this.endDate = endDate;
		this.type = type;
	}

	public RegisteredUser getCreatoreViaggio() {
		return creatoreViaggio;
	}

	public Collection<RegisteredUser> getPartecipantiViaggio() {
		return partecipantiViaggio;
	}

	public void setPartecipantiViaggio(Collection<RegisteredUser> partecipantiViaggio) {
		this.partecipantiViaggio = partecipantiViaggio;
	}

	public Collection<Poll> getPollList() {
		return pollList;
	}

	public void setPollList(Collection<Poll> pollList) {
		this.pollList = pollList;
	}

	public void setRoute(Route route) {
		this.route = route;
	}


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


	public Route getRoute()
	{
		return this.route;
	}




}