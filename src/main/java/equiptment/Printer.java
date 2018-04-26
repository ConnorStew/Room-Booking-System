package equiptment;

import java.util.Arrays;

import data.Equiptment;

public class Printer extends Equiptment {
	
	private String[] paperTypes;

	public Printer(String[] tags, String name, String[] paperTypes) {
		super(tags, name);
	}

	/**
	 * @return the paperTypes
	 */
	public String[] getPaperTypes() {
		return paperTypes;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Printer [paperTypes=" + Arrays.toString(paperTypes) + ", tags=" + Arrays.toString(tags) + ", name="
				+ name + "]";
	}

	
}
