package UserSubsystem;

import java.util.*;

public class RegisteredUser extends User {
	/**
	*Classe che estende la classe user e rappresenta un utente registrato al sistema.
	*@param email = email address utente
	*@param username = username unico dell'utente
	*@param password = password utente
	*@param nome = nome utente
	*@param cognome = cognome utente
	*@param birthDate = data di nascita dell'utente
	*@param age = eta' dell'utente
	*
	*/
	private String email;
	private String username;
	private String password;
	private String nome;
	private String cognome;
	private String birthDate;
	private int age;
	private int authorization;
	
	public RegisteredUser(String email, String username, String password, String nome, String cognome,
			String birthDate, int age) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.birthDate = birthDate;
		this.age = age;
	}


	public RegisteredUser(int id, String email, String username, String password, String nome, String cognome,
			String birthDate, int age) {
		super(id);
		this.email = email;
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.birthDate = birthDate;
		this.age = age;
	}
	/**
	*Metodo che restituisce l'username dell'utente
	*@return username
	*
	*/
	public String getUsername() {
		return this.username;
	}

	/**
	 * Metodo che setta l'username dell'utente
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	*Metodo che restituisce l'email address dell'utente
	*@return email
	*
	*/
	public String getEmail() {
		return this.email;
	}

	/**
	 * Metodo che setta l'email address dell'utente
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	*Metodo che restituisce il nome dell'utente
	*@return nome
	*
	*/

	public String getNome() {
		return this.nome;
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
		return this.cognome;
	}

	/**
	 * Metodo che setta il cognome dell'utente
	 * @param cognome
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	/**
	*Metodo che restituisce la data di nascita dell'utente
	*@return birthDate
	*
	*/
	public String getBirthDate() {
		return birthDate;
	}

	/**
	 * Metodo che setta la data di nascita dell'utente
	 * @param birthDate
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	/**
	*Metodo che restituisce l'eta' dell'utente
	*@return age
	*
	*/
	public int getAge() {
		return this.age;
	}

	/**
	 * Metodo che setta l'eta' dell'utente
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	public void setAuthorization(int autor){
		authorization = autor;
	}
	
	public int getAuthorization(){
		return this.authorization;
	}
}