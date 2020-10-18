package propets.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NumberItemsOnPageException extends RuntimeException {
	
	public NumberItemsOnPageException(String reason) {
		super(reason);
	}

}
