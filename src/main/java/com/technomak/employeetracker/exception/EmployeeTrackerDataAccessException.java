/**
 * 
 */
package com.technomak.employeetracker.exception;

/**
 * @author JaY
 *
 */
public class EmployeeTrackerDataAccessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3232942354028041536L;

	public EmployeeTrackerDataAccessException(String message) {
		super(message);
	}

	public EmployeeTrackerDataAccessException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
