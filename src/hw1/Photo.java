package hw1;

public class Photo {

	static int count = 0;
	private int id;
	private String name;
	private String description;
	private String location;
	
	public Photo(String name, String location) {
		this.id = count;
		this.name = name;
		this.location = location;
		count++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getId() {
		return id;
	}
}
