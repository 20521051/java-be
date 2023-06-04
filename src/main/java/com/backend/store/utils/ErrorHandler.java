// package com.backend.store.utils;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.RestController;
// import
// org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
// import com.backend.store.utils.HttpException;
// import com.backend.store.utils.ValidationException;

// @ControllerAdvice
// @RestController
// public class ErrorHandler extends ResponseEntityExceptionHandler {

// @ExceptionHandler(ValidationException.class)
// public ResponseEntity<ErrorResponse>
// handleValidationException(ValidationException ex) {
// ErrorResponse errorResponse = new
// ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Validation Failed",
// ex.getFields());
// return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
// }

// @ExceptionHandler(HttpException.class)
// public ResponseEntity<ErrorResponse> handleHttpException(HttpException ex) {
// ErrorResponse errorResponse = new ErrorResponse(ex.getStatusCode(),
// ex.getError());
// return new ResponseEntity<>(errorResponse,
// HttpStatus.valueOf(ex.getStatusCode()));
// }

// @ExceptionHandler(Exception.class)
// public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
// ErrorResponse errorResponse = new
// ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
// "Internal Server Error");
// return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
// }

// public static class ErrorResponse {
// private int statusCode;
// private String message;
// private Object details;

// public ErrorResponse(int statusCode, String message) {
// this.statusCode = statusCode;
// this.message = message;
// }

// public ErrorResponse(int statusCode, String message, Object details) {
// this.statusCode = statusCode;
// this.message = message;
// this.details = details;
// }

// // Getters and Setters
// }
// }
