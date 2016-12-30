package UserSubsystem;

import java.util.*;

public class RegisteredUser extends User {

	private String email;
	private String username;
	private String password;
	private String nome;
	private String cognome;
	private GregorianCalendar birthDate;
	private int age;

	public RegisteredUser(String email, String username, String password, String nome, String cognome,
			GregorianCalendar birthDate, int age) {
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
			GregorianCalendar birthDate, int age) {
		super(id);
		this.email = email;
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.birthDate = birthDate;
		this.age = age;
	}

	public String getUsername() {
		return this.username;
	}

	/**
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return this.email;
	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return this.nome;
	}

	/**
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return this.cognome;
	}

	/**
	 * 
	 * @param cognome
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public GregorianCalendar getBirthDate() {
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param bithDate
	 */
	public void setBirthDate(GregorianCalendar birthDate) {
		this.birthDate = birthDate;
	}

	public int getAge() {
		return this.age;
	}

	/**
	 * 
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}

}