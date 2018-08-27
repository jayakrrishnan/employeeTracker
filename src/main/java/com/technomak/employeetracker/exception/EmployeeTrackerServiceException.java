/**
 * 
 */
package com.technomak.employeetracker.exception;

/**
 * @author JaY
 *
 */
public class EmployeeTrackerServiceException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2853146757927156650L;

	public EmployeeTrackerServiceException(String message) {
		super(message);
	}

	public EmployeeTrackerServiceException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
