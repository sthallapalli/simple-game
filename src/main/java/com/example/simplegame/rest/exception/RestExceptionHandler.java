package com.example.simplegame.rest.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.simplegame.framework.exception.RuleEvaluationException;

/**
 * @author <a href="mailto:sthallapalli@outlook.com">sthallapalli</a>
 */

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(RuleEvaluationException.class)
	public ResponseEntity<ApiException> handleRuleEvaluationException(RuleEvaluationException ex) {
		ApiException exception = new ApiException(HttpStatus.EXPECTATION_FAILED, ex.getMessage(), ex);
		return new ResponseEntity<>(exception, exception.getStatus());
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ApiException> handleException(RuntimeException ex) {
		ApiException exception = new ApiException(HttpStatus.EXPECTATION_FAILED, ex.getMessage(), ex);
		return new ResponseEntity<>(exception, exception.getStatus());
	}
}
 