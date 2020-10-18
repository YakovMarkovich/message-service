package propets.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Matchers.anyInt;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import propets.dto.Location;
import propets.dto.LostFoundPost;
import propets.dto.Post;
import propets.service.ILostFoundService;
import propets.service.IPostService;

@WebMvcTest
public class LostFoundTests {
	static final String REST_URL = "/propets-app.herokuapp.com/lostfound";
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@MockBean
	private ILostFoundService service;

	@Test
	public void createOneLostPost() throws Exception {
		String[] photos = new String[5];
		photos[0] = "https://someimage.jpg";
		String[] tags = { "tag1", "tag2", "tag3", "color1", "color2" };
		Location location = new Location("Israel", "Tel Aviv", "Herzl", 10, 31.78, 35.23);
		LostFoundPost post = new LostFoundPost(57777L, true, LocalDateTime.now(), "cat", "mail", "doberman", tags,
				photos, location);
		Mockito.when(service.createLostPost(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(),
				Mockito.any())).thenReturn(post);
		MockHttpServletResponse response = mockMvc
				.perform(post(REST_URL + "/lost").content(objectMapper.writeValueAsString(post))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(content().json(objectMapper.writeValueAsString(post)))
				.andReturn().getResponse();
		response.getHeader("Access-Control-Allow-Methods").equals("GET, OPIONS, HEAD, PUT, POST, DELETE");
		response.getHeader("Date").equals(LocalDateTime.now().toString());
	}

	@Test
	public void createOneFoundPost() throws Exception {
		String[] photos = new String[5];
		photos[0] = "https://someimage.jpg";
		String[] tags = { "tag1", "tag2", "tag3", "color1", "color2" };
		Location location = new Location("Israel", "Tel Aviv", "Herzl", 10, 31.78, 35.23);
		LostFoundPost post = new LostFoundPost(57777L, true, LocalDateTime.now(), "cat", "mail", "doberman", tags,
				photos, location);
		Mockito.when(service.createFoundPost(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(),
				Mockito.any())).thenReturn(post);
		MockHttpServletResponse response = mockMvc
				.perform(post(REST_URL + "/found").content(objectMapper.writeValueAsString(post))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(content().json(objectMapper.writeValueAsString(post)))
				.andReturn().getResponse();
		response.getHeader("Access-Control-Allow-Methods").equals("GET, OPIONS, HEAD, PUT, POST, DELETE");
		response.getHeader("Date").equals(LocalDateTime.now().toString());
	}

	@Test
	public void getPostOfLostPets() throws Exception {
		String[] photos = new String[5];
		photos[0] = "https://someimage.jpg";
		String[] tags1 = { "tag1", "tag2", "tag3", "color1", "color2" };
		String[] tags2 = { "tag33", "tag2", "tag32", "color1", "color12" };
		Location location1 = new Location("Israel", "Tel Aviv", "Herzl", 10, 31.78, 35.23);
		Location location2 = new Location("Israel", "Rehovot", "Herzl", 10, 32.78, 15.23);
		LostFoundPost[] posts = new LostFoundPost[2];
		posts[0] = new LostFoundPost(57777L, true, LocalDateTime.now(), "cat", "mail", "doberman", tags1, photos,
				location1);
		posts[1] = new LostFoundPost(57778L, true, LocalDateTime.now(), "dog", "mail", "doberman", tags2, photos,
				location2);
		Mockito.when(service.getPostsLost(anyInt(), anyInt())).thenReturn(posts);
		mockMvc.perform(get(REST_URL + "/losts?itemsOnPage=10&currentPage=0").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(posts)));
	}

	@Test
	public void getPostOfFoundtPets() throws Exception {
		String[] photos = new String[5];
		photos[0] = "https://someimage.jpg";
		String[] tags1 = { "tag1", "tag2", "tag3", "color1", "color2" };
		String[] tags2 = { "tag33", "tag2", "tag32", "color1", "color12" };
		Location location1 = new Location("Israel", "Tel Aviv", "Herzl", 10, 31.78, 35.23);
		Location location2 = new Location("Israel", "Rehovot", "Herzl", 10, 32.78, 15.23);
		LostFoundPost[] posts = new LostFoundPost[2];
		posts[0] = new LostFoundPost(57777L, true, LocalDateTime.now(), "cat", "mail", "doberman", tags1, photos,
				location1);
		posts[1] = new LostFoundPost(57778L, true, LocalDateTime.now(), "dog", "mail", "doberman", tags2, photos,
				location2);
		Mockito.when(service.getPostsFound(anyInt(), anyInt())).thenReturn(posts);
		mockMvc.perform(get(REST_URL + "/founds?itemsOnPage=10&currentPage=0").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(posts)));
	}

	@Test
	public void getPost() throws Exception {
		String[] photos = new String[5];
		photos[0] = "https://someimage.jpg";
		String[] tags = { "tag1", "tag2", "tag3", "color1", "color2" };
		Location location = new Location("Israel", "Tel Aviv", "Herzl", 10, 31.78, 35.23);
		LostFoundPost post = new LostFoundPost(48584L, true, LocalDateTime.now(), "cat", "mail", "doberman", tags,
				photos, location);
		Mockito.when(service.getPost(Mockito.any())).thenReturn(post);
		mockMvc.perform(get(REST_URL + "/48584").content(objectMapper.writeValueAsString(post))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(48584))
				.andExpect(content().json(objectMapper.writeValueAsString(post)));
	}

	@Test
	public void updatePost() throws Exception {

		String[] photos = new String[5];
		photos[0] = "https://someimage.jpg";
		String[] tags = { "tag1", "tag2", "tag3", "color1", "color2" };
		Location location = new Location("Israel", "Tel Aviv", "Herzl", 10, 31.78, 35.23);
		LostFoundPost post = new LostFoundPost(48584L, true, LocalDateTime.now(), "cat", "mail", "doberman", tags,
				photos, location);
		Mockito.when(service.getPost(Mockito.any())).thenReturn(post);
		Mockito.when(service.updatePost(Mockito.any(), Mockito.any())).thenReturn(post);

		mockMvc.perform(put(REST_URL + "/48584").content(objectMapper.writeValueAsString(post))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(48584))
				.andExpect(content().json(objectMapper.writeValueAsString(post))).andReturn().getResponse();
	}

	@Test
	public void deletePost() throws Exception {
		String[] photos = new String[5];
		photos[0] = "https://someimage.jpg";
		String[] tags = { "tag1", "tag2", "tag3", "color1", "color2" };
		Location location = new Location("Israel", "Tel Aviv", "Herzl", 10, 31.78, 35.23);
		LostFoundPost post = new LostFoundPost(48584L, true, LocalDateTime.now(), "cat", "mail", "doberman", tags,
				photos, location);
		Mockito.when(service.getPost(Mockito.any())).thenReturn(post);
		Mockito.when(service.deletePost(Mockito.any())).thenReturn(post);
		mockMvc.perform(delete(REST_URL + "/48584").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(48584))
				.andExpect(content().json(objectMapper.writeValueAsString(post)));
	}

	@Test
	public void getUserPosts() throws Exception {
		String[] photos = new String[5];
		photos[0] = "https://someimage.jpg";
		String[] tags1 = { "tag1", "tag2", "tag3", "color1", "color2" };
		String[] tags2 = { "tag33", "tag2", "tag32", "color1", "color12" };
		Location location1 = new Location("Israel", "Tel Aviv", "Herzl", 10, 31.78, 35.23);
		Location location2 = new Location("Israel", "Rehovot", "Herzl", 10, 32.78, 15.23);
		LostFoundPost[] posts = new LostFoundPost[2];
		posts[0] = new LostFoundPost(57777L, true, LocalDateTime.now(), "cat", "mail", "doberman", tags1, photos,
				location1);
		posts[1] = new LostFoundPost(57778L, true, LocalDateTime.now(), "dog", "mail", "doberman", tags2, photos,
				location2);
		//Mockito.when(service.getUserPosts(Mockito.any())).thenReturn(posts);
		/*mockMvc.perform(post(REST_URL + "/userdata")
				.content(objectMapper.writeValueAsString(new User(0, "serednyak", "yakov", "http://image.jpg")))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(posts)));*/
	}

	@Test
	public void getTagsAndColorsOfPicture() throws Exception {
		String[] tags = { "kitten", "cat", "kitty", "feline", "pet", "animal", "fur", "domestic", "cute", "eyes",
				"whiskers", "gold", "olive green", "brown" };
		Mockito.when(service.getTagsAndColorsOfPicture(Mockito.any()))
		.thenReturn(tags);
		mockMvc.perform(get(REST_URL + "/tagscolors?image_url=https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSZ0qMEPmLM9wS7BIavp6K-8cIhzVWEqTYF-JIM8Aeao7x7-Rtc")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(tags)));
	}
	
	@Test
	public void searchPostsLost() throws Exception {
		String[] photos = new String[5];
		photos[0] = "https://someimage.jpg";
		String[] tags1 = { "tag1", "tag2", "tag3", "color1", "color2" };
		String[] tags2 = { "tag33", "tag2", "tag32", "color1", "color12" };
		Location location1 = new Location("Israel", "Tel Aviv", "Herzl", 10, 31.78, 35.23);
		Location location2 = new Location("Israel", "Rehovot", "Herzl", 10, 32.78, 15.23);
		LostFoundPost[] posts = new LostFoundPost[2];
		posts[0] = new LostFoundPost(57777L, true, LocalDateTime.now(), "cat", "mail", "doberman", tags1, photos,
				location1);
		posts[1] = new LostFoundPost(57778L, true, LocalDateTime.now(), "dog", "mail", "doberman", tags2, photos,
				location2);
		Mockito.when(service.searchPostsLost(anyInt(), anyInt(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
		.thenReturn(posts);
		mockMvc.perform(post(REST_URL + "/lost/filter?currentPage=0&itemsOnPage=10")
				.content(objectMapper.writeValueAsString(new LostFoundPost("cat", "pitbulle", tags1, location1)))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(posts)));
	}
	
	@Test
	public void searchPostsFound() throws Exception {
		String[] photos = new String[5];
		photos[0] = "https://someimage.jpg";
		String[] tags1 = { "tag1", "tag2", "tag3", "color1", "color2" };
		String[] tags2 = { "tag33", "tag2", "tag32", "color1", "color12" };
		Location location1 = new Location("Israel", "Tel Aviv", "Herzl", 10, 31.78, 35.23);
		Location location2 = new Location("Israel", "Rehovot", "Herzl", 10, 32.78, 15.23);
		LostFoundPost[] posts = new LostFoundPost[2];
		posts[0] = new LostFoundPost(57777L, true, LocalDateTime.now(), "cat", "mail", "doberman", tags1, photos,
				location1);
		posts[1] = new LostFoundPost(57778L, true, LocalDateTime.now(), "dog", "mail", "doberman", tags2, photos,
				location2);
		Mockito.when(service.searchPostsFound(anyInt(), anyInt(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
		.thenReturn(posts);
		mockMvc.perform(post(REST_URL + "/founds/filter?currentPage=0&itemsOnPage=10")
				.content(objectMapper.writeValueAsString(new LostFoundPost("cat", "pitbulle", tags1, location1)))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(posts)));
	}
}
