package propets.config.filter;

import java.io.IOException;
import java.net.URI;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import propets.dto.AuthResponse;

@Service
@Order(20)
public class AuthenticationFilter implements Filter {

	@Autowired
	RestTemplate restTemplate;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		System.out.println("I am in doFilter");
		System.out.println(request.getServletPath());
		//System.out.println(request.getServletPath().matches("/propets-app.herokuapp.com/message/*"));
		//System.out.println(request.getServletPath().matches("/propets-app.herokuapp.com/message/petrusha@gmail.com"));
		//System.out.println(request.getServletPath().matches("/propets-app.herokuapp.com/message/.*"));
		//System.out.println(request.getServletPath().matches("/propets-app.herokuapp.com/message/[[-A-Z0-9+&@#/%?=~_|!:,.;]*"));
		if (checkEndpoint(request.getServletPath(), request.getMethod())) {
			try {
				System.out.println("I am in doFilter try");
				String token = request.getHeader("X-Token");
				Enumeration headerNames = request.getHeaderNames();
			    while (headerNames.hasMoreElements()) {
			        String key = (String) headerNames.nextElement();
			        String value = request.getHeader(key);
			        System.out.println(key +" " + value);;
			    }
				System.out.println("I am in doFilter try token " + token);
				if (token != null) {
					HttpHeaders headers = new HttpHeaders();
					headers.add("X-Token", token);
					/*RequestEntity<String> requestEntity = new RequestEntity<String>(headers, HttpMethod.GET,
							new URI("http://localhost:8080/propets-app.herokuapp.com/account/token/validation"));*/
					RequestEntity<String> requestEntity = new RequestEntity<String>(headers, HttpMethod.GET,
							new URI("http://accounting-service.herokuapp.com/account/en/v1/token/validation"));
					ResponseEntity<AuthResponse> responseEntity = restTemplate.exchange(requestEntity, AuthResponse.class);
					System.out.println("response login " + responseEntity.getBody().getLogin());
					System.out.println("response " + responseEntity.getStatusCodeValue());
					req.setAttribute("login", responseEntity.getBody().getLogin());
					req.setAttribute("name",  responseEntity.getBody().getUserName());
					req.setAttribute("avatar", responseEntity.getBody().getAvatar());
					if(!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
						response.sendError(403);
						return;
					}
				}	
				else {
					response.sendError(403, "Token not sended");
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(403, "Token Validation Failed");;
				return;
			}
		}
		chain.doFilter(request, response);
		
	}

	private boolean checkEndpoint(String path, String method) {
		//boolean res = path.matches("/propets-app.herokuapp.com/message/.*");
		boolean res = path.matches("/message/en/v1/.*");
		return res;
	}

}
