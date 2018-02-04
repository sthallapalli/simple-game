package com.example.simplegame.framework.rules;

import java.util.List;

/**
 * @author <a href="mailto:sthallapalli@outlook.com">sthallapalli</a>
 */

public class RuleExecutor {

	public static boolean execute(List<Rule<? extends RuleContext>> rules) {
		boolean finalResult = true;
		
		for (Rule<? extends RuleContext> rule : rules) {
			finalResult = finalResult && rule.process();
		}
		
		return finalResult;
	}
}
