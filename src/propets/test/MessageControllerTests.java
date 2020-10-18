package propets.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
import propets.dto.Post;
import propets.entities.GeneralPost;
import propets.service.IPostService;
import propets.service.SequenceGeneratorService;

@WebMvcTest
public class MessageControllerTests {
	
	static final String REST_URL = "/propets-app.herokuapp.com/message";
	    @Autowired
	    private MockMvc mockMvc;
	    @Autowired
	    private ObjectMapper objectMapper;
	    @MockBean
	    private IPostService service;
	    @MockBean
		private SequenceGeneratorService sequenceGeneratorService;
	    
	    /*
	    @Test
	    public void createOnePost() throws Exception {
	    	String[] photos = new String[5];
	    	photos[0] = "https://someimage.jpg";
	    	LocalDateTime ld = LocalDateTime.now();
	        GeneralPost post  = new GeneralPost(48584L, "helenjohnson@gmail.com", "Anna Smith", 
	        		"https://www.gravatar.com/avatar/0?d=mp","I want you", ld, photos);
	        Post postDto  = new Post(48584L, "I want you", ld, photos);
	        Mockito.when(service.createPost(Mockito.any(), Mockito.any())).thenReturn(post);
	        MockHttpServletResponse response = mockMvc.perform(
	                post(REST_URL)
	                        .content(objectMapper.writeValueAsString(post))
	                        .contentType(MediaType.APPLICATION_JSON)
	        )
	                .andExpect(status().isCreated())
	                .andExpect(content().json(objectMapper.writeValueAsString(postDto)))
	                .andReturn().getResponse();
	        response.getHeader("Access-Control-Allow-Methods").equals("GET, OPIONS, HEAD, PUT, POST, DELETE");
	    }
	    /*
	    
	    @Test
	    public void updatePost() throws Exception {

	        String[] photos = new String[5];
	    	photos[0] = "https://someimage.jpg";
	        Post post  = new Post(48584L, "I want you", LocalDateTime.now(), photos);
	        Mockito.when(service.getPost(Mockito.any())).thenReturn(post);
	        Mockito.when(service.updatePost(Mockito.any(), Mockito.any())).thenReturn(post);
	      
	        mockMvc.perform(
	                put(REST_URL+ "/48584")
	                        .content(objectMapper.writeValueAsString(post))
	                        .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.id").value(48584))
	                .andExpect(content().json(objectMapper.writeValueAsString(post)))
	                .andReturn().getResponse();
	    }
	    
	    @Test
	    public void getPost() throws Exception {
	        String[] photos = new String[5];
	    	photos[0] = "https://someimage.jpg";
	        Post post  = new Post(48584L, "I want you", LocalDateTime.now(), photos);
	        Mockito.when(service.getPost(Mockito.any())).thenReturn(post);
	         
	        mockMvc.perform(
	                get(REST_URL+ "/48584")
	                .content(objectMapper.writeValueAsString(post))
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.id").value(48584))
	                .andExpect(content().json(objectMapper.writeValueAsString(post)));	          
	    }
	    
	    @Test
	    public void deletePost() throws Exception {

	        String[] photos = new String[5];
	    	photos[0] = "https://someimage.jpg";
	        Post post  = new Post(48584L, "I want you", LocalDateTime.now(), photos);
	        Mockito.when(service.getPost(Mockito.any())).thenReturn(post);
	        Mockito.when(service.deletePost(Mockito.any())).thenReturn(post);
	        mockMvc.perform(
	                delete(REST_URL+ "/48584")      
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.id").value(48584))
	                .andExpect(content().json(objectMapper.writeValueAsString(post)));
	    }
	    
	    @Test
	    public void getPostPageable() throws Exception {
	        String[] photos = new String[5];
	    	photos[0] = "https://someimage.jpg";
	    	Post[] posts = new Post[2];
	        posts[0]  = new Post(48584L, "I want you", LocalDateTime.now(), photos);
	        posts[1]  = new Post(48585L, "I want you back", LocalDateTime.now(), photos);
	        Mockito.when(service.getPostsPageable(anyInt(), anyInt())).thenReturn(posts);
	        mockMvc.perform(
	                get(REST_URL+"/view?itemsOnPage=10&currentPage=0")      
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(content().json(objectMapper.writeValueAsString(posts)));	          
	    }
	    
	    @Test
	    public void getUserPosts() throws Exception {
	        String[] photos = new String[5];
	    	photos[0] = "https://someimage.jpg";
	    	Post[] posts = new Post[2];
	        posts[0]  = new Post(48584L, "I want you", LocalDateTime.now(), photos);
	        posts[1]  = new Post(48585L, "I want you back", LocalDateTime.now(), photos);
	        Mockito.when(service.getUserPosts(Mockito.any())).thenReturn(posts);
	        mockMvc.perform(
	                post(REST_URL+"/userdata")  
	                .content(objectMapper.writeValueAsString(new User(0, "serednyak", "yakov", "http://image.jpg")))
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(content().json(objectMapper.writeValueAsString(posts)));	          
	    }
	    */
}
