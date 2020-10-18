package propets.controllers;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import propets.dto.Location;
import propets.dto.LostFoundPost;
import propets.dto.Post;
import propets.dto.UserDto;
import propets.service.ILostFoundService;
import propets.service.IPostService;

@RestController
@RequestMapping(value = LostFoundController.REST_URL)
public class LostFoundController {
	static final String REST_URL = "/propets-app.herokuapp.com/lostfound/";
	private Logger log = LoggerFactory.getLogger(LostFoundController.class);


	private ILostFoundService service;

	@PostMapping("/lost")
	public ResponseEntity<LostFoundPost> createLostPost(@RequestBody LostFoundPost p) {
		LostFoundPost post = service.createLostPost(p.getType(), p.getSex(), p.getBreed(), p.getLocation(), p.getImages(), p.getTags());
		HttpHeaders responseHeaders = createHeaders();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(REST_URL + "/{id}")
				.buildAndExpand(post.getId()).toUri();
		// в боди ложим не только пост а + юзерНейм, юзерЛогин, аватар
		return ResponseEntity.created(location).headers(responseHeaders).body(post);
	}
	
	@PostMapping("/found")
	public ResponseEntity<LostFoundPost> createFoundPost(@RequestBody LostFoundPost p) {
		LostFoundPost post = service.createFoundPost(p.getType(), p.getSex(), p.getBreed(), p.getLocation(), p.getImages(), p.getTags());
		HttpHeaders responseHeaders = createHeaders();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(REST_URL + "/{id}")
				.buildAndExpand(post.getId()).toUri();
		// в боди ложим не только пост а + юзерНейм, юзерЛогин, аватар
		return ResponseEntity.created(location).headers(responseHeaders).body(post);
	}
	
	@GetMapping("/losts")
    public ResponseEntity<LostFoundPost[]> getPostsOfLostPets(@RequestParam int itemsOnPage,
            @RequestParam int currentPage)  {
		LostFoundPost [] posts = service.getPostsLost(itemsOnPage,currentPage);
		if (posts != null) {
			HttpHeaders responseHeaders = createHeaders();
			//неясно зачем в боди пихают itemsOnPage и currentPage 
			return ResponseEntity.ok().headers(responseHeaders).body(posts);
		}
		return ResponseEntity.notFound().build();
    }
	
	@GetMapping("/founds")
    public ResponseEntity<LostFoundPost[]> getPostsOfFoundPets(@RequestParam int itemsOnPage,
            @RequestParam int currentPage)  {
		LostFoundPost [] posts = service.getPostsFound(itemsOnPage,currentPage);
		if (posts != null) {
			HttpHeaders responseHeaders = createHeaders();
			//неясно зачем в боди пихают itemsOnPage и currentPage 
			return ResponseEntity.ok().headers(responseHeaders).body(posts);
		}
		return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LostFoundPost> getPost(@PathVariable("id") Long id)  {
		LostFoundPost post = service.getPost(id);
		if (post != null) {
			HttpHeaders responseHeaders = createHeaders();
			return ResponseEntity.ok().headers(responseHeaders).body(post);
		}
		return ResponseEntity.notFound().build();
    }

	@PutMapping(value = "/{id}")
	public ResponseEntity<LostFoundPost> update(@RequestBody LostFoundPost p,
            @PathVariable("id") Long id) {
		// будет проверка на консистентность юзердата
		LostFoundPost  oldPost = service.getPost(id);
		LostFoundPost  newPost;
		if (oldPost != null) {
			oldPost.setBreed(p.getBreed());;
			oldPost.setImages(p.getImages());
			oldPost.setSex(p.getSex());
			oldPost.setType(p.getType());
			oldPost.setLocation(p.getLocation());
			oldPost.setTags(p.getTags());
			newPost = service.updatePost(id, oldPost);
			HttpHeaders responseHeaders = createHeaders();
			return ResponseEntity.ok().headers(responseHeaders).body(newPost);
		}
		return ResponseEntity.notFound().build();
	}
	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<LostFoundPost> delete(@PathVariable("id") Long id) {
		// будет проверка на консистентность юзердата
		LostFoundPost post = service.getPost(id);
		if (post != null) {
			post = service.deletePost(id);
			HttpHeaders responseHeaders = createHeaders();
			return ResponseEntity.ok().headers(responseHeaders).body(post);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping(value = "/userdata")
	public ResponseEntity<LostFoundPost[]> getUserPosts(@RequestBody UserDto userDto) {
		LostFoundPost [] posts = service.getUserPosts(userDto);
		if (posts != null) {
			HttpHeaders responseHeaders = createHeaders();
			return ResponseEntity.ok().headers(responseHeaders).body(posts);
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/tagscolors")
    public ResponseEntity<String[]> getTagsAndColorsOfPicture(@RequestParam String image_url)  {
		String [] tags = service.getTagsAndColorsOfPicture(image_url);
		if (tags != null) {
			HttpHeaders responseHeaders = createHeaders();
			//неясно зачем в боди пихают itemsOnPage и currentPage 
			return ResponseEntity.ok().headers(responseHeaders).body(tags);
		}
		return ResponseEntity.notFound().build();
    }
    
    @PostMapping("/lost/filter")
    public ResponseEntity<LostFoundPost[]> searchPostsLost(@RequestBody LostFoundPost p, @RequestParam int itemsOnPage,
            @RequestParam int currentPage)  {
		LostFoundPost [] posts = service.searchPostsLost(itemsOnPage, currentPage, p.getBreed(), p.getBreed(), p.getTags(), p.getLocation());
		if (posts != null) {
			HttpHeaders responseHeaders = createHeaders();
			//неясно зачем в боди пихают itemsOnPage и currentPage 
			return ResponseEntity.ok().headers(responseHeaders).body(posts);
		}
		return ResponseEntity.notFound().build();
    }
    
    @PostMapping("/founds/filter")
    public ResponseEntity<LostFoundPost[]> searchPostsFound(@RequestBody LostFoundPost p,
    		@RequestParam int itemsOnPage,
            @RequestParam int currentPage)  {
		LostFoundPost [] posts = service.searchPostsFound(itemsOnPage, currentPage, p.getBreed(), p.getBreed(), p.getTags(), p.getLocation());
		if (posts != null) {
			HttpHeaders responseHeaders = createHeaders();
			//неясно зачем в боди пихают itemsOnPage и currentPage 
			return ResponseEntity.ok().headers(responseHeaders).body(posts);
		}
		return ResponseEntity.notFound().build();
    }
    
	
	private HttpHeaders createHeaders() {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST, DELETE");
		responseHeaders.set("Access-Control-Allow-Headers", "Content-Type, X-Token");
		responseHeaders.set("Transfer-Encoding:", "chunked");
		responseHeaders.set("Date", LocalDateTime.now().toString());
		return responseHeaders;
	}
}
