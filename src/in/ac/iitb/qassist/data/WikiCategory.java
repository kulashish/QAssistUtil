package in.ac.iitb.qassist.data;

import java.util.List;

public class WikiCategory {

	private String name;
	private int id;
	private List<WikiCategory> subcategories;
	private boolean hasSub;

	public WikiCategory() {
		hasSub = true;
	}

	public boolean isHasSub() {
		return hasSub;
	}

	public void setHasSub(boolean hasSub) {
		this.hasSub = hasSub;
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

	public void setId(int i) {
		this.id = i;
	}

	public List<WikiCategory> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<WikiCategory> subcategories) {
		this.subcategories = subcategories;
	}

}
