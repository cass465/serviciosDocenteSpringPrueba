package co.edu.unicundi.SpringPrueba.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import co.edu.unicundi.SpringPrueba.dto.ErrorWrapper;

@ControllerAdvice
@RestController
public class ExceptionsFilter extends ResponseEntityExceptionHandler {

	// Excepciones web
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) { // 400

		ex.printStackTrace();

		String statusString = status.toString().split(" ")[0];
		String statusPhrase = status.getReasonPhrase();
		String message = "";
		String path = request.getDescription(false).replaceAll("uri=", "");

		if (ex.getMessage().contains("Required request body")) {
			message = "La petición necesita body";
		} else if (ex.getMessage().contains("JSON parse error")) {
			message = "Revisar la sintaxis del body en la petición";
		}

		ErrorWrapper error = new ErrorWrapper(statusString, statusPhrase, message, path);
		return new ResponseEntity<Object>(error, status);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) { // 400

		String errors = "";
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors += error.getField().toUpperCase() + ": " + error.getDefaultMessage() + ". ";
		}

		String statusString = status.toString().split(" ")[0];
		String statusPhrase = status.getReasonPhrase();
		String message = errors;
		String path = request.getDescription(false).replaceAll("uri=", "");

		ErrorWrapper error = new ErrorWrapper(statusString, statusPhrase, message, path);
		return new ResponseEntity<Object>(error, status);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) { // 404

		ex.printStackTrace();

		String statusString = status.toString().split(" ")[0];
		String statusPhrase = status.getReasonPhrase();
		String message = "URL " + ex.getRequestURL() + " no existe";
		String path = request.getDescription(false).replaceAll("uri=", "");

		ErrorWrapper error = new ErrorWrapper(statusString, statusPhrase, message, path);
		return new ResponseEntity<Object>(error, status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) { // 405

		ex.printStackTrace();

		String statusString = status.toString().split(" ")[0];
		String statusPhrase = status.getReasonPhrase();
		String message = "Método " + ex.getMethod() + " no soportado, el método correcto es "
				+ ex.getSupportedMethods()[0];
		String path = request.getDescription(false).replaceAll("uri=", "");

		ErrorWrapper error = new ErrorWrapper(statusString, statusPhrase, message, path);
		return new ResponseEntity<Object>(error, status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) { // 415

		ex.printStackTrace();

		String statusString = status.toString().split(" ")[0];
		String statusPhrase = status.getReasonPhrase();
		String message = "Tipo de medio " + ex.getContentType().getType() + "/" + ex.getContentType().getSubtype()
				+ " no soportado";
		String path = request.getDescription(false).replaceAll("uri=", "");

		ErrorWrapper error = new ErrorWrapper(statusString, statusPhrase, message, path);
		return new ResponseEntity<Object>(error, status);
	}

	// Personalizados
	@ExceptionHandler(RegisteredObjectException.class)
	public ResponseEntity<ErrorWrapper> filterRegisteredObjectException(RegisteredObjectException ex,
			WebRequest request) {

		ex.printStackTrace();

		String status = HttpStatus.CONFLICT.toString().split(" ")[0];
		String statusPhrase = HttpStatus.CONFLICT.getReasonPhrase();
		String path = request.getDescription(false).replaceAll("uri=", "");

		ErrorWrapper error = new ErrorWrapper(status, statusPhrase, ex.getMessage(), path);
		return new ResponseEntity<ErrorWrapper>(error, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(FieldRequiredException.class)
	public ResponseEntity<ErrorWrapper> filterFieldRequiredException(FieldRequiredException ex, WebRequest request) {
		ex.printStackTrace();

		String status = HttpStatus.BAD_REQUEST.toString().split(" ")[0];
		String statusPhrase = HttpStatus.BAD_REQUEST.getReasonPhrase();
		String path = request.getDescription(false).replaceAll("uri=", "");

		ErrorWrapper error = new ErrorWrapper(status, statusPhrase, ex.getMessage(), path);
		return new ResponseEntity<ErrorWrapper>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ListNoContentException.class)
	public ResponseEntity<?> filterListNoContentException(ListNoContentException ex, WebRequest request) {
		ex.printStackTrace();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<ErrorWrapper> filterObjectNotFoundException(ObjectNotFoundException ex, WebRequest request) {
		ex.printStackTrace();

		String status = HttpStatus.NOT_FOUND.toString().split(" ")[0];
		String statusPhrase = HttpStatus.NOT_FOUND.getReasonPhrase();
		String path = request.getDescription(false).replaceAll("uri=", "");

		ErrorWrapper error = new ErrorWrapper(status, statusPhrase, ex.getMessage(), path);
		return new ResponseEntity<ErrorWrapper>(error, HttpStatus.NOT_FOUND);
	}

	// Exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorWrapper> filterException(Exception ex, WebRequest request) {
		System.out.println("EXCEPTION: " + ex.getClass().getCanonicalName()); // Imprime el nombre de la Exception que
																				// se lanzó
		ex.printStackTrace();

		String status = HttpStatus.INTERNAL_SERVER_ERROR.toString().split(" ")[0];
		String statusPhrase = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
		String path = request.getDescription(false).replaceAll("uri=", "");

		ErrorWrapper error = new ErrorWrapper(status, statusPhrase, ex.getMessage(), path);
		return new ResponseEntity<ErrorWrapper>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
