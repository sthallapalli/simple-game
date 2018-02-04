package com.example.simplegame.framework.rules;

import java.util.Map;

/**
 * @author <a href="mailto:sthallapalli@outlook.com">sthallapalli</a>
 */

public abstract class RuleContext {

	private Map<String, Object> contextData;

	protected void setRuleContextAdditionalData(Map<String, Object> contextData) {
		this.contextData = contextData;
	}
	
	protected Map<String, Object> getRuleContextData() {
		return this.contextData;
	}
}
