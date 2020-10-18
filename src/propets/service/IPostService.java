package propets.service;

import propets.entities.GeneralPost;
import propets.dto.GeneralPostsDto;
import propets.dto.ListPosts;
import propets.dto.Post;
import propets.dto.PostUpdateDto;

public interface IPostService {
	GeneralPost createPost(String login, String userName, String avatar,  String text, String[] images);
	GeneralPost updatePost(Long id, PostUpdateDto post, String login);
	GeneralPost deletePost(Long id);
	GeneralPost getPost(Long id);
	GeneralPostsDto getPostsPageable(int itemsOnPage, int pageNumber, String login);
	GeneralPost[] getUserPosts(Long[] longs);
	

}
