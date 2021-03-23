package co.edu.unicundi.SpringPrueba.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class ListNoContentException extends Exception {

	private static final long serialVersionUID = 1L;

	public ListNoContentException() {
		
	}
}
