package co.edu.unicundi.SpringPrueba.dto;

import java.time.LocalDateTime;

public class ErrorWrapper {
	
	private String timestamp;
	private String status;
	private String error;
	private String message;
	private String path;
	/**
	 * @param status
	 * @param error
	 * @param message
	 * @param path
	 */
	public ErrorWrapper(String status, String error, String message, String path) {
		this.timestamp = LocalDateTime.now().toString();
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}
	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}
	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
}
