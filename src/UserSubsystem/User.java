package UserSubsystem;
/**
*Classe astratta rappresentante un utente che utilizza il sistema.
*@param id=identificativo dell'utente
*
*/
public abstract class User {

	private int id;

	public User() {
	}

	public User(int id) {
		this.id = id;
	}
	/**
	*Metodo che restituisce l'identificativo dell'utente
	*@return id
	*
	*/
	public int getId() {
		return this.id;
	}

	/**
	 * Metodo che setta l'identificativo dell'utente
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

}