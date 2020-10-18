package propets.service;

import propets.dto.Location;
import propets.dto.LostFoundPost;
import propets.dto.UserDto;

public interface ILostFoundService {
	LostFoundPost createLostPost(String type, String sex,
			String breed, Location location, String[] images, String[] tags);
	LostFoundPost createFoundPost(String type, String sex,
			String breed, Location location, String[] images, String[] tags);
	LostFoundPost[] getPostsLost(int itemsOnPage, int pageNumber);
	LostFoundPost[] getPostsFound(int itemsOnPage, int pageNumber);
	LostFoundPost getPost(Long id);
	LostFoundPost updatePost(Long id, LostFoundPost post);
	LostFoundPost deletePost(Long id);
	LostFoundPost[] getUserPosts(UserDto user);
	LostFoundPost[] searchPostsFound(int itemsOnPage, int pageNumber, 
			String type, String breed, String[] tags,  Location location);
	LostFoundPost[] searchPostsLost(int itemsOnPage, int pageNumber, 
			String type, String breed, String[] tags,  Location location);
	String[] getTagsAndColorsOfPicture(String imageUrl);

}
