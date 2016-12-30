package UserSubsystem;

public abstract class User {

	private int id;

	public User() {
	}

	public User(int id) {
		this.id = id;
	}

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

}