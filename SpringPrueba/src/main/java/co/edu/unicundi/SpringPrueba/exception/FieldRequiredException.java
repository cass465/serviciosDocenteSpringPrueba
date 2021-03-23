package co.edu.unicundi.SpringPrueba.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FieldRequiredException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public FieldRequiredException(String message) {
		super(message);
	}
}
