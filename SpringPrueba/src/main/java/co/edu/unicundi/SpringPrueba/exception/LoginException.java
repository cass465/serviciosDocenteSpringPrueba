package co.edu.unicundi.SpringPrueba.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class LoginException extends Exception {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public LoginException(String message) {
		super(message);
	}

}
