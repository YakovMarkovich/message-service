package propets.controllers;

import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import propets.dto.Post;
import propets.service.IFavouritesActivitiesService;

@RestController
@RequestMapping(value = FavoritesActivitiesController.REST_URL)
public class FavoritesActivitiesController {
	static final String REST_URL = "/propets-app.herokuapp.com/favouritesactivities";
	private Logger log = LoggerFactory.getLogger(FavoritesActivitiesController.class);

	private IFavouritesActivitiesService service;

	
	@GetMapping("/favourite/{login}")
    public ResponseEntity<Post[]> getFavouriteMessagesPosts(@PathVariable("login") String login)  {
		Post[] posts = service.getFavouritesMessagesPosts(login);
		if (posts != null) {
			HttpHeaders responseHeaders = createHeaders(login);
			return ResponseEntity.ok().headers(responseHeaders).body(posts);
		}
		return ResponseEntity.notFound().build();
    }
	
	
	private HttpHeaders createHeaders(String login) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Date", LocalDateTime.now().toString());
		responseHeaders.set("Connection", "keep-alive");
		responseHeaders.set("Transfer-Encoding:", "chunked");
		responseHeaders.set("Keep-Alive", "timeout=60");
		return responseHeaders;
	}


	
}
