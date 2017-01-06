package PollSubsystem;

import java.util.GregorianCalendar;
/**
*Classe che rappresenta un sondaggio.
*@param id = identificativo del sondaggio
*@param description = descrizion del sondaggio
*@param vnegative = valutazione negativa
*@param vpositive = valutazione positiva
*@param startDate = data inizio sondaggio
*@param endDate = data fine sondaggio
*
*/
public class Poll {

	private int id;
	private String description;
	private int vnegative;
	private int vpositive;
	private String startDate;
	private String endDate;

	public String getEndDate() {
		return this.endDate;
	}	
	
	public Poll(int id, String description, int vnegative, int vpositive, String startDate, String endDate) {
		super();
		this.id = id;
		this.description = description;
		this.vnegative = vnegative;
		this.vpositive = vpositive;
		this.startDate = startDate;
		this.endDate = endDate;
	}


	public Poll(String description, String startDate, String endDate) {
		super();
		this.description = description;
		this.vnegative = 0;
		this.vpositive = 0;
		this.startDate = startDate;
		this.endDate = endDate;
	}



	/**
	*Metodo che setta la data di fine.
	*@param endDate
	*/
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	*Metodo che restituisce la data di inizio.
	*@return startDate
	*/
	public String getStartDate() {
		return this.startDate;
	}

	/**
	*Metodo che setta la data di inizio.
	*@param startDate
	*/
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	*Metodo che restituisce l'identificativo del sondaggio.
	*@return id
	*/
	public int getId() {
		return this.id;
	}

	/**
	*Metodo che setta l'identificativo del sondaggio.
	*@param id
	*/
	public void setId(int id) {
		this.id = id;
	}
	/**
	*Metodo che restituisce la descrizione del sondaggio.
	*@return description
	*/
	public String getDescription() {
		return this.description;
	}

	/**
	*Metodo che setta la descrizione del sondaggio.
	*@param description
	*/
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	*Metodo che restituisce la valutazione negativa.
	*@return description
	*/
	public int getVnegative() {
		return this.vnegative;
	}

	/**
	*Metodo che setta la valutazione negativa.
	*@param description
	*/
	/*public void setVnegative(int vnegative) {
		this.vnegative = vnegative;
	}*/
	/**
	*Metodo che restituisce la valutazione positiva.
	*@return description
	*/
	public int getVpositive() {
		return this.vpositive;
	}

	/**
	*Metodo che setta la valutazione positiva.
	*@param description
	*/
	/*public void setVpositive(int vpositive) {
		this.vpositive = vpositive;
	}*/
	
	public void registerVote(int vote) {
		if (vote > 0) {
			vpositive++;
		} else if (vote < 0) {
			vnegative++;
		}
	}

}