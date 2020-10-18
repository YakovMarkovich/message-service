package propets.dto;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

public class GeneralPostsDto {
	private int itemsTotal;
	private int itemsOnPage;
	private int currentPage;
	Post[] posts;
	
	public GeneralPostsDto() {}
	
	public GeneralPostsDto(int itemsTotal, int itemsOnPage, int currentPage, Post[] posts) {
		super();
		this.itemsTotal = itemsTotal;
		this.itemsOnPage = itemsOnPage;
		this.currentPage = currentPage;
		this.posts = posts;
	}

	public int getItemsTotal() {
		return itemsTotal;
	}

	public void setItemsTotal(int itemsTotal) {
		this.itemsTotal = itemsTotal;
	}

	public int getItemsOnPage() {
		return itemsOnPage;
	}

	public void setItemsOnPage(int itemsOnPage) {
		this.itemsOnPage = itemsOnPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public Post[] getPosts() {
		return posts;
	}

	public void setPosts(Post[] posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "GeneralPostsDto [itemsTotal=" + itemsTotal + ", itemsOnPage=" + itemsOnPage + ", currentPage="
				+ currentPage + ", posts=" + Arrays.toString(posts) + "]";
	}
	
	
	
	
}