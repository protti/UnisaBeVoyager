package UserSubsystem;
/**
*Rappresenta l'amministratore del sistema. Gestisce i dati sensibili dell'utente.
*@param email = email address utente
*@param password = password utente
*@param nome = nome utente
*@param cognome = cognome utente
*
*/
public class SystemAdmin extends User {

	private String email;
	private String password;
	private String nome;
	private String cognome;
	
	public SystemAdmin(String email, String password, String nome, String cognome) {
		super();
		this.email = email;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
	}

	public SystemAdmin(int id, String email, String password, String nome, String cognome) {
		super(id);
		this.email = email;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;	
	}

	/**
	*Metodo che restituisce l'email address dell'utente
	*@return email
	*
	*/
	public String getEmail() {
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	/**
	 * Metodo che setta l'email address dell'utente
	 * @param email
	 */
	public void setEmail(String email) {
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}
	/**
	*Metodo che restituisce la password dell'utente
	*@return password
	*
	*/
	public String getPassword() {
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	/**
	 * Metodo che setta la password dell'utente
	 * @param password
	 */
	public void setPassword(String password) {
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}
	/**
	*Metodo che restituisce il nome dell'utente
	*@return nome
	*
	*/
	public String getNome() {
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	/**
	 * Metodo che setta il nome dell'utente
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	*Metodo che restituisce il cognome dell'utente
	*@return cognome
	*
	*/
	public String getCognome() {
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	/**
	 * Metodo che setta il cognome dell'utente
	 * @param cognome
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

}