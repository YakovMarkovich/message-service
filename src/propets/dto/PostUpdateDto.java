package propets.dto;

import java.io.Serializable;
import java.util.Arrays;


@SuppressWarnings("serial")
public class PostUpdateDto implements Serializable {
	
	private String text;
	private String[] images;
	
	public PostUpdateDto() {}

	public PostUpdateDto(String text, String[] images) {
		super();
		this.text = text;
		this.images = images;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String[] getImages() {
		return images;
	}

	public void setImages(String[] images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "PostUpdateDto [text=" + text + ", images=" + Arrays.toString(images) + "]";
	}
	
	

}
