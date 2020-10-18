package propets.dto;

import java.io.Serializable;
import java.util.Arrays;


@SuppressWarnings("serial")
public class ListPosts implements Serializable  {
	Long[] posts;
	
	public ListPosts() {}

	public ListPosts(Long[] posts) {
		super();
		this.posts = posts;
	}

	public Long[] getPosts() {
		return posts;
	}

	public void setPosts(Long[] posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "ListPosts [posts=" + Arrays.toString(posts) + "]";
	}
	
	
	
	
	
}