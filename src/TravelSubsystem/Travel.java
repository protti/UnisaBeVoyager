package TravelSubsystem;


import UserSubsystem.*;
import java.util.*;

import PollSubsystem.Poll;
import RouteSubsystem.Route;

public class Travel {
	private static final String TravelMananger = null;
	/**
	*Classe che rappresenta un viaggio che viene creato da un utente registrato.
	*@param creatoreViaggio = utente registrato che crea il viaggio
	*@param partecipantiViaggio = arraylist rappresentante gli utenti regitrati partecipanti al viaggio
	*@param pollList = arraylist contenente i sondaggi su tale viaggio
	*@param route = itinerario del viaggio
	*@param id = identificativo del viaggio
	*@param startDate = data di inizio
	*@param endDate = data di fine
	*@param type= tipo di viaggio
	*/
	
	private RegisteredUser creatoreViaggio;
	private ArrayList<RegisteredUser> partecipantiViaggio;
	private ArrayList<Poll> pollList;
	private Route route;
	private int id;
	private String nome;
	private String startDate;
	private String endDate;
	private boolean confirmed;
	
	/**
	 * Metodo che dice se un viaggio e' confermato.
	 * @return confirmed
	 */
	public boolean isConfirmed() {
		return confirmed;
	}
	
	/**
	 * Metodo che setta la conferma di un viaggio.
	 * @param confirmed
	 */
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	
	/**
	 * Metodo che restituisce il nome.
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Metodo che setta il nome.
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Travel(int id, String nome, Route route, RegisteredUser creatoreViaggio, String startDate,
			String endDate, boolean type) {
		this.nome = nome;
		this.creatoreViaggio = creatoreViaggio;
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.type = type;
		this.route = route;
		partecipantiViaggio = new ArrayList<RegisteredUser>();
	}

	public Travel(String nome, Route route, RegisteredUser creatoreViaggio, String startDate,
			String endDate, boolean type) {
		this.nome = nome;
		this.creatoreViaggio = creatoreViaggio;
		this.startDate = startDate;
		this.endDate = endDate;
		this.type = type;
		this.route = route;
		partecipantiViaggio = new ArrayList<RegisteredUser>();
	}
	/**
	*Metodo che restituisce l'utente creatore del viaggio.
	*@return creatoreViaggio
	*
	*/
	public RegisteredUser getCreatoreViaggio() {
		return creatoreViaggio;
	}
	/**
	*Metodo che restituisce l'arraylist dei partecipanti al viaggio.
	*@return partecipantiViaggio
	*
	*/
	public ArrayList<RegisteredUser> getPartecipantiViaggio() {
		return partecipantiViaggio;
	}
	/**
	*Metodo che setta i componenti dell'arraylist dei partecipanti al viaggio.
	*@param partecipantiViaggio
	*
	*/
	
	/* Mi sembra inutile avere un metodo che prende tutta un'arraylist, meglio passargli l'utente e farlo aggiungere. Rinominiamo il metodo "aggiungiPartecipante" magari*/
	
	public boolean addUserToTravel(RegisteredUser user) { 
		
		this.partecipantiViaggio.add(user);
		return true;
		
	}
			
	/**
	*Metodo che restituisce l'arraylist dei sondaggi del viaggio.
	*@return pollList
	*/
	
	public ArrayList<Poll> getPollList() {
		return pollList;
	}
	/**
	*Metodo che setta i sondaggi sul del viaggio in arraylist.
	*@param pollList
	*
	*/
	public void setPollList(ArrayList<Poll> pollList) {
		this.pollList = pollList;
	}
	/**
	*Metodo che setta l'itinerario del viaggio.
	*@param route
	*
	*/
	public void setRoute(Route route) {
		this.route = route;
	}


	
	private boolean type;
	/**
	*Metodo che restituisce l'identificativo del viaggio.
	*@return id
	*
	*/
	public int getId() {
		return this.id;
	}

	/**
	*Metodo che setta l'identificativo del viaggio.
	*@param id
	*
	*/
	public void setId(int id) {
		this.id = id;
	}
	/**
	*Metodo che restituisce la data d'inizio.
	*@return startDate
	*
	*/
	public String getStartDate() {
		return this.startDate;
	}

	/**
	*Metodo che setta la data d'inizio.
	*@param startDate
	*
	*/
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	*Metodo che restituisce la data di fine.
	*@return endDate
	*
	*/
	public String getEndDate() {
		return this.endDate;
	}

	/**
	*Metodo che setta la data di fine.
	*@param endDate
	*
	*/
	public void GregorianCalendar(String endDate) {
		this.endDate = endDate;
	}
	/**
	*Metodo che restituisce il tipo del viaggio.
	*@return type
	*
	*/
	public boolean getType() {
		return this.type;
	}

	/**
	 * Imposta a false se il viaggio non � modificabile, oppure a true se � modificabile.
	 * @param type
	 */
	public void setType(boolean type) {
		this.type = type;
	}

	/**
	*Metodo che restituisce l'itinerario del viaggio.
	*@return route
	*
	*/
	public Route getRoute()
	{
		return this.route;
	}
	/**
	*Metodo che chiude un viaggio.
	*
	*/
	public void closeTravel()
	{
		this.type = false;
	}


}