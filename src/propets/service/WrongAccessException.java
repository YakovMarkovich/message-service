package propets.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class WrongAccessException extends RuntimeException {
	
	public WrongAccessException(String reason) {
		super(reason);
	}

}
