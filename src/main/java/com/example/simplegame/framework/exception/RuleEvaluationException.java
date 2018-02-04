package com.example.simplegame.framework.exception;

/**
 * @author <a href="mailto:sthallapalli@outlook.com">sthallapalli</a>
 */

public class RuleEvaluationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2111719771586446997L;

	public RuleEvaluationException() {
		super();
	}

	public RuleEvaluationException(String message) {
		super(message);
	}

	public RuleEvaluationException(String message, Throwable th) {
		super(message, th);
	}

}
