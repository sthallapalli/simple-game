package com.example.simplegame.framework.rules.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.example.simplegame.framework.rules.enums.FailureLevel;

/**
 * @author <a href="mailto:sthallapalli@outlook.com">sthallapalli</a>
 */

@Retention(RUNTIME)
@Target(TYPE)
public @interface RuleQualifier {

	FailureLevel level() default FailureLevel.WARNING;
}
