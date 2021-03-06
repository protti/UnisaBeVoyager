package LocationSubsystem;
/**
*Classe che rappresenta un luogo.
*@param name = nome del luogo
*@param id = identificativo del luogo
*@param descrizione = descrizione del luogo
*/
public class Location {

	private String name;
	private int id;
	private String descrizione;
	
	public Location(String name, String descrizione) {
		super();
		this.name = name;
		this.descrizione = descrizione;
	}
	
	public Location(int id,String name, String descrizione) {
		super();
		this.id = id;
		this.name = name;
		this.descrizione = descrizione;
	}
	
	/**
	*Metodo che restituisce il nome del luogo.
	*@return name
	*/
	public String getName() {
		return name;
	}
	/**
	*Metodo che setta il nome del luogo.
	*@param name
	*/
	public void setName(String name) {
		this.name = name;
	}
	/**
	*Metodo che restituisce l'dentificativo del luogo.
	*@return id
	*/
	public int getId() {
		return id;
	}
	/**
	*Metodo che setta l'identificativo del luogo.
	*@param id
	*/
	public void setId(int id) {
		this.id = id;
	}
	/**
	*Metodo che restituisce la descrizione del luogo.
	*@return description
	*/
	public String getDescrizione() {
		return descrizione;
	}
	/**
	*Metodo che setta la descrizione del luogo.
	*@param description
	*/
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public boolean equals(Object anObject){
		if(anObject == null) return false;
		if(!(anObject instanceof Location)) return false;
		Location obj = (Location) anObject;
		return this.descrizione.equals(obj.descrizione) && 
				this.name.equals(obj.name) &&
				this.id == obj.id;
	}
	
}