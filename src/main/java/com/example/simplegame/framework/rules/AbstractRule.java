package com.example.simplegame.framework.rules;

import java.lang.annotation.Annotation;
import java.util.Arrays;

import com.example.simplegame.framework.rules.annotation.RuleQualifier;
import com.example.simplegame.framework.rules.enums.FailureLevel;

/**
 * @author <a href="mailto:sthallapalli@outlook.com">sthallapalli</a>
 */

public abstract class AbstractRule<T extends RuleContext> implements Rule<T> {

	private T context;

	protected AbstractRule(T context) {
		this.context = context;
	}

	public abstract boolean process();

	protected T getRuleContext() {
		return this.context;
	}

	protected FailureLevel getRuleFailureLevel() {
		Annotation[] annotations = this.getClass().getAnnotations();

		Annotation ann = Arrays.stream(annotations)
				.filter(annotation -> annotation.annotationType() == RuleQualifier.class).findFirst().get();

		if (ann == null)
			return FailureLevel.WARNING;

		RuleQualifier ruleQualifier = (RuleQualifier) ann;
		return ruleQualifier.level();
	}
}
