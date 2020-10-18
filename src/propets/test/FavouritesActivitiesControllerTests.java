package propets.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import propets.service.IFavouritesActivitiesService;

@WebMvcTest
public class FavouritesActivitiesControllerTests {
	static final String REST_URL =  "/propets-app.herokuapp.com/favouritesactivities";
	    @Autowired
	    private MockMvc mockMvc;
	    @Autowired
	    private ObjectMapper objectMapper;
	    @MockBean
	    private IFavouritesActivitiesService service;
	    
	    /*@Test
	    public void getFavouriteMessagesPosts() throws Exception {
	        String[] photos = new String[5];
	    	photos[0] = "https://someimage.jpg";
	    	Post[] posts = new Post[2];
	        posts[0]  = new Post(48584L, "I want you", LocalDateTime.now(), photos);
	        posts[1]  = new Post(48585L, "I want you back", LocalDateTime.now(), photos);
	        Mockito.when(service.getFavouritesMessagesPosts(Mockito.any())).thenReturn(posts);
	        MockHttpServletResponse response =  
	        		mockMvc.perform(
	        		 get(REST_URL+"/favourite/serednyak1988")      
		                .contentType(MediaType.APPLICATION_JSON))
		                .andExpect(status().isOk())
		                .andExpect(content().json(objectMapper.writeValueAsString(posts)))
		                .andReturn().getResponse();
	        response.getHeader("Keep-Alive").equals("timeout=60");
	        response.getHeader("Date").equals(LocalDateTime.now().toString());
	        
	    }*/
}
