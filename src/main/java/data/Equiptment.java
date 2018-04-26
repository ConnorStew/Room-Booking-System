package data;

/**
 * This class is used to store information about a piece of equipment.
 * @author Connor Stewart
 */
public class Equiptment {
	
	protected String[] tags;
	
	protected String name;

	public Equiptment(String[] tags, String name) {
		this.tags = tags;
		this.name = name;
	}

	/**
	 * @return the tags
	 */
	public String[] getTags() {
		return tags;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

}
