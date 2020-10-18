package propets.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import propets.dao.PostRepository;
import propets.dto.GeneralPostsDto;
import propets.dto.ListPosts;
import propets.dto.Post;
import propets.dto.PostUpdateDto;
import propets.entities.GeneralPost;

@Service
public class PostServiceImpl implements IPostService {

	@Autowired
	PostRepository repository;

	@Autowired
	SequenceGeneratorService sequenceGeneratorService;

	@Override
	public GeneralPost createPost(String login, String userName, String avatar, String text, String[] images) {
		return repository.save(new GeneralPost(sequenceGeneratorService.generateSequence(GeneralPost.SEQUENCE_NUMBER),
				login, userName, avatar, text, LocalDateTime.now(), images));
	}

	@Override
	public GeneralPost updatePost(Long id, PostUpdateDto post, String login) {
		GeneralPost oldPost = repository.findById(id).orElse(null);
		System.out.println("old post " + post);
		if (oldPost != null) {
			if (login.equals(oldPost.getUserLogin())) {
				oldPost.setText(post.getText());
				oldPost.setImages(post.getImages());
				return repository.save(oldPost);
			} else
				throw new WrongAccessException("User not owns this post for updating");
		} else {
			return null;
		}
	}

	@Override
	public GeneralPost deletePost(Long id) {
		GeneralPost post = repository.findById(id).orElse(null);
		if (post != null) {
			repository.deleteById(id);
			return post;
		} else
			return null;
	}

	@Override
	public GeneralPost getPost(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public GeneralPostsDto getPostsPageable(int itemsOnPage, int pageNumber, String login) {
		System.out.println("login from principal " + login);
		GeneralPost[] posts = repository.findAllByUserLogin(login).orElse(null);
		System.out.println("posts " + posts);
		System.out.println("posts bavaria" + repository.findAllByUserLogin("bavaria@gmail.com").orElse(null));
		if (posts.length != 0) {
			try {
				GeneralPost[] genPosts = Arrays.asList(posts)
						.subList(pageNumber * itemsOnPage, pageNumber * itemsOnPage + itemsOnPage)
						.toArray(new GeneralPost[itemsOnPage]);
				return new GeneralPostsDto(posts.length, itemsOnPage, pageNumber, convertToDtoArr(genPosts));
			} catch (Exception e) {
				throw new NumberItemsOnPageException("Wrong number items for  this page");
			}
		}
		return null;
	}

	@Override
	public GeneralPost[] getUserPosts(Long[] ids) {
		System.out.println(ids.length);
		System.out.println(ids);
		List<GeneralPost> posts =
		Arrays.asList(ids).stream().map(e->repository.findById(e).orElse(null))
		.filter(s-> s!=null)
		.collect(Collectors.toList());
		System.out.println(posts);
		return posts.toArray(new GeneralPost[posts.size()]);
	}

	private Post[] convertToDtoArr(GeneralPost[] posts) {
		Post[] postsArray = new Post[posts.length];
		for (int i = 0; i < postsArray.length; i++) {
			postsArray[i] = new Post(posts[i].getId(), posts[i].getUserLogin(), posts[i].getUserName(),
					posts[i].getAvatar(), posts[i].getText(), posts[i].getDatePost(), posts[i].getImages());
		}
		return postsArray;
	}

}
