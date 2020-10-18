package propets.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;

@SuppressWarnings("serial")
public class LostFoundPost implements Serializable{
	
	private Long id;
	private boolean typePost;
	private LocalDateTime datePost;
	private String type;
	private String sex;
	private String breed;
	private String[] tags;
	private String[] images;
	private Location location;
	
	public LostFoundPost() {}
	public LostFoundPost(Long id, boolean typePost, LocalDateTime datePost, String type, String sex, String breed,
			String[] tags, String[] images, Location location) {
		super();
		this.id = id;
		this.typePost = typePost;
		this.datePost = datePost;
		this.type = type;
		this.sex = sex;
		this.breed = breed;
		this.tags = tags;
		this.images = images;
		this.location = location;
	}
	
	public LostFoundPost( String type,  String breed, String[] tags, Location location) {
		super();
		this.type = type;
		this.breed = breed;
		this.tags = tags;
		this.location = location;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isTypePost() {
		return typePost;
	}
	public void setTypePost(boolean typePost) {
		this.typePost = typePost;
	}
	public LocalDateTime getDatePost() {
		return datePost;
	}
	public void setDatePost(LocalDateTime datePost) {
		this.datePost = datePost;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public String[] getTags() {
		return tags;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	public String[] getImages() {
		return images;
	}
	public void setImages(String[] images) {
		this.images = images;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((breed == null) ? 0 : breed.hashCode());
		result = prime * result + ((datePost == null) ? 0 : datePost.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + Arrays.hashCode(images);
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + Arrays.hashCode(tags);
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + (typePost ? 1231 : 1237);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LostFoundPost other = (LostFoundPost) obj;
		if (breed == null) {
			if (other.breed != null)
				return false;
		} else if (!breed.equals(other.breed))
			return false;
		if (datePost == null) {
			if (other.datePost != null)
				return false;
		} else if (!datePost.equals(other.datePost))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (!Arrays.equals(images, other.images))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (!Arrays.equals(tags, other.tags))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (typePost != other.typePost)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "LostFoundPost [id=" + id + ", typePost=" + typePost + ", datePost=" + datePost + ", type=" + type
				+ ", sex=" + sex + ", breed=" + breed + ", tags=" + Arrays.toString(tags) + ", images="
				+ Arrays.toString(images) + ", location=" + location + "]";
	}
	
	

	
	
	
	
	
  
    
	

}
