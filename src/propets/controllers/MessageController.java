package propets.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import propets.dto.GeneralPostsDto;
import propets.dto.ListPosts;
import propets.dto.Post;
import propets.dto.PostUpdateDto;
import propets.entities.GeneralPost;
import propets.dto.UserDto;
import propets.service.IPostService;
import propets.service.SequenceGeneratorService;

@RestController
@RequestMapping(value = MessageController.REST_URL)
public class MessageController {
//	static final String REST_URL = "/propets-app.herokuapp.com/message";
	static final String REST_URL = "/message/en/v1";
	private Logger log = LoggerFactory.getLogger(MessageController.class);

	@Autowired
	private IPostService service;

	@Autowired
	SequenceGeneratorService sequenceGeneratorService;

	@Autowired
	RestTemplate restTemplate;

	@PostMapping("/{login}")
	public ResponseEntity<?> createPost(@RequestBody PostUpdateDto p, @PathVariable("login") String login, ServletRequest req, @RequestHeader("X-Token") String token, HttpServletResponse response) throws IOException  {
		//p.setId(sequenceGeneratorService.generateSequence(GeneralPost.SEQUENCE_NUMBER));
		HttpServletRequest request = (HttpServletRequest) req;
		String userName = (String) request.getAttribute("name");
		String avatar =  (String) request.getAttribute("avatar");
		GeneralPost post = service.createPost(login, userName, avatar, p.getText(), p.getImages());
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(REST_URL + "/{id}")
				.buildAndExpand(post.getId()).toUri();
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("X-ServiceName", "message");
		responseHeaders.set("X-Token", token);
		
		Long postId = post.getId();
		String userLogin = post.getUserLogin();
		try {
			//RequestEntity<String> requestEntity = new RequestEntity<String>(responseHeaders, HttpMethod.PUT, new URI(
			//		"http://localhost:8080/propets-app.herokuapp.com/account/" + userLogin + "/activity/" + postId));
			RequestEntity<String> requestEntity = new RequestEntity<String>(responseHeaders, HttpMethod.PUT, new URI(
					"https://accounting-service.herokuapp.com/account/en/v1/" + userLogin + "/activity/" + postId));
			System.out.println("request Entiry " + requestEntity);
			ResponseEntity<UserDto> resp = restTemplate.exchange(requestEntity, UserDto.class);
			resp.getHeaders().forEach((k, v) -> System.out.println(k + " " + v));
			responseHeaders.addAll(resp.getHeaders());
		} catch (RestClientException e) {
			//return ResponseEntity.status(HttpStatus.FORBIDDEN)
		    //        .body("Token not corresponds to login");
			response.sendError(403, "Token not corresponds to login");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return ResponseEntity.created(location).headers(responseHeaders).body(convertToDto(post));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Post> update(@RequestBody PostUpdateDto p, @PathVariable("id") Long id,
			ServletRequest req) {
		// будет проверка на консистентность юзердата
		HttpServletRequest request = (HttpServletRequest) req;
		String login = (String) request.getAttribute("login");
		System.out.println("login in controller  " + login);
		GeneralPost post = service.updatePost(id, p, login);
		if (post != null) {
			HttpHeaders responseHeaders = createHeaders();
			return ResponseEntity.ok().headers(responseHeaders).body(convertToDto(post));
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Post> getPost(@PathVariable("id") Long id) {
		GeneralPost post = service.getPost(id);
		if (post != null) {
			HttpHeaders responseHeaders = createHeaders();
			return ResponseEntity.ok().headers(responseHeaders).body(convertToDto(post));
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/view")
	public ResponseEntity<GeneralPostsDto> getPostsPageable(@RequestParam int itemsOnPage,
			@RequestParam int currentPage, ServletRequest req) {
		HttpServletRequest request = (HttpServletRequest) req;
		String login = (String) request.getAttribute("login");
		System.out.println("login in controller  " + login);
		GeneralPostsDto posts = service.getPostsPageable(itemsOnPage, currentPage, login);
		if (posts != null) {
			HttpHeaders responseHeaders = createHeaders();
			return ResponseEntity.ok().headers(responseHeaders).body(posts);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Post> delete(@PathVariable("id") Long id, HttpServletResponse response,  @RequestHeader("X-Token") String token) throws IOException {
		// будет проверка на консистентность юзердата
		System.out.println("I' m in delete");
		GeneralPost post = service.getPost(id);
		if (post != null) {
			HttpHeaders responseHeaders = createHeaders();
			responseHeaders.set("X-ServiceName", "message");
			responseHeaders.set("X-token", token);
			Long postId = post.getId();
			String userLogin = post.getUserLogin();

			try {
				/*RequestEntity<String> requestEntity = new RequestEntity<String>(responseHeaders, HttpMethod.DELETE,
						new URI("http://localhost:8080/propets-app.herokuapp.com/account/" + userLogin + "/activity/"
								+ postId));*/
				RequestEntity<String> requestEntity = new RequestEntity<String>(responseHeaders, HttpMethod.DELETE,
						new URI("http://accounting-service.herokuapp.com/account/en/v1/" + userLogin + "/activity/"
								+ postId));
				System.out.println("request Entiry " + requestEntity);
				ResponseEntity<UserDto> responseEntity = restTemplate.exchange(requestEntity, UserDto.class);
				responseHeaders.addAll(responseEntity.getHeaders());
				if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
					service.deletePost(id);
				} else if (responseEntity.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
					response.sendError(404, "Post not found");
				} else {
					System.out.println("Im in user not own");
					response.sendError(403, "User not owns this post");
				}
			} catch (RestClientException e) {
				System.out.println("Im in user not own catch");
				response.sendError(403, "User not owns this post");
				// return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}

			return ResponseEntity.ok().headers(responseHeaders).body(convertToDto(post));
		} else {
			System.out.println("I m in 404");
			response.sendError(404, "Post not found");
		}
		return ResponseEntity.notFound().build();
	}
	
	
	@PostMapping(value = "/userdata")
	public ResponseEntity<?> getUserPosts(@RequestBody ListPosts posts) {
		GeneralPost[] gposts = service.getUserPosts(posts.getPosts());
		if (gposts != null) {
			HttpHeaders responseHeaders = createHeaders();
			return ResponseEntity.ok().headers(responseHeaders).body(convertToDtoArr(gposts));
		}
		return ResponseEntity.notFound().build();
	}

	private Post convertToDto(GeneralPost post) {
		return new Post(post.getId(), post.getUserLogin(), post.getUserName(), post.getAvatar(), post.getText(),
				post.getDatePost(), post.getImages());
	}
	
	
	private Post[] convertToDtoArr(GeneralPost[] posts) {
		Post[] postsArray = new Post[posts.length];
		for (int i = 0; i < postsArray.length; i++) {
			postsArray[i] = new Post(posts[i].getId(), posts[i].getUserLogin(), posts[i].getUserName(),
					posts[i].getAvatar(), posts[i].getText(), posts[i].getDatePost(), posts[i].getImages());
		}
		return postsArray;
	}

	private HttpHeaders createHeaders() {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Access-Control-Allow-Origin", "*");
		responseHeaders.add("Access-Control-Allow-Methods", "GET, OPTIONS, POST, PUT, DELETE");
		responseHeaders.add("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Token");
		return responseHeaders;
	}
}
