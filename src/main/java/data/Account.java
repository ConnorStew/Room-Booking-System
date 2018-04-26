package data;

/**
 * This class is used to store information about an account.
 * @author Connor Stewart
 */
public class Account {
	
	/** The accounts id in the database. */
	private int id;
	
	/** Whether this account is locked. */
	private boolean locked;
	
	/** The username of this account. */
	private String username;
	
	/** The password of this account. */
	private String password;
	
	/** The type of the account. */
	private AccountType type;

	/**
	 * Creates an account.
	 * @param id the accounts id in the database
	 * @param username the accounts username
	 * @param password the accounts passwords
	 * @param locked whether this account is locked
	 * @param type the accounts type
	 */
	public Account(int id, String username, String password, boolean locked, AccountType type) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.locked = locked;
		this.type = type;
	}

	/**
	 * @return the locked
	 */
	public boolean isLocked() {
		return locked;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the type
	 */
	public AccountType getType() {
		return type;
	}
	
	public int getID() {
		return id;
	}

}
