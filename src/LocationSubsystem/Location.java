package LocationSubsystem;

public class Location {

	private String name;
	private int id;
	private String descrizione;
	
	public Location(int id,String name, String descrizione) {
		super();
		this.name = name;
		this.id = id;
		this.descrizione = descrizione;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	
	
}