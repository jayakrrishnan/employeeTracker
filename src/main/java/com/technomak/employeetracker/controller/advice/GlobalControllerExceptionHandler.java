/**
 * 
 */
package com.technomak.employeetracker.controller.advice;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.technomak.employeetracker.exception.EmployeeTrackerServiceException;

/**
 * @author JaY
 *
 */
@RestControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

	public static final Logger log = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

	@ExceptionHandler(EmployeeTrackerServiceException.class)
	public ResponseEntity<Object> handleServiceException(EmployeeTrackerServiceException exception) {
		log.error("EmployeeServiceException occured", exception);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(prepareMessage(exception.getMessage()));
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		log.error("MethodArgumentNotValid...check request body");
		final List<String> errors = new ArrayList<String>();
		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	private String prepareMessage(String message) {
		return "\"" + message + "\"";
	}
}
