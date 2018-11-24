package hw1;

import java.util.ArrayList;

public class PhotoAlbum {
	
	static int count = 0;
	private int id;
	private String name;
	private String description;
	private String location;
	private ArrayList<Photo> photos;
	
	public PhotoAlbum(String name, String description, String location) {
		this.name = name;
		this.description = description;
		this.location = location;
		photos = new ArrayList<>();
		this.id = count;
		count++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public ArrayList<Photo> getPhotos() {
		ArrayList<Photo> copy = (ArrayList<Photo>) photos.clone();
		
		return copy;
	}
	
	public Photo getPhoto(int id) {
		int foundIndex = -1;
		
		for ( int i = 0 ; i < photos.size() ; i++ ) {
			if ( photos.get(i).getId() == id) {
				foundIndex = i;
			}
		}
		
		return photos.get(foundIndex);
	}
	
	public void addPhoto(Photo photo) {
		photos.add(photo);
	}
	
	public void removePhoto(int id) {
		for ( int i = 0 ; i < photos.size() ; i++) {
			if (photos.get(i).getId() == id) {
				photos.remove(i);
				break;
			}
		}
		
	}
	

}
