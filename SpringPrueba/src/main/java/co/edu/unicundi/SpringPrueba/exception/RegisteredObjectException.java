package co.edu.unicundi.SpringPrueba.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class RegisteredObjectException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public RegisteredObjectException(String message) {
		super(message);
	}
}
