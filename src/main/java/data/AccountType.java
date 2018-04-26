package data;

/**
 * This enumeration is used to store the different types of accounts.
 * @author Connor Stewart
 */
public enum AccountType {
	
	User("user"), Admin("admin");
	
	/** The string used to represent this AccountType in the database. */
	private final String databaseString;
	
	/**
	 * Define an AccountType.
	 * @param databaseString the String used to represent this AccountType in the database
	 */
	private AccountType(String databaseString) {
		this.databaseString = databaseString;
	}
	
	/**
	 * Gets the account type based on the string used to store this account type in the database.
	 * @param text the String from the database
	 * @return the corresponding AccountType
	 */
	public static AccountType parseTypeString(String text) {
		for (AccountType type : AccountType.values())
			if (type.databaseString.equals(text))
				return type;
		
		//return null if no matching type is found
		return null;
	}
	
	@Override
	public String toString() {
		return databaseString;
	}	
}
