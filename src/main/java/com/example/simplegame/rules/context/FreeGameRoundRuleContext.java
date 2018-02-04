package com.example.simplegame.rules.context;

import com.example.simplegame.domain.PlayRequest;
import com.example.simplegame.framework.rules.RuleContext;
/**
 * @author <a href="mailto:sthallapalli@outlook.com">sthallapalli</a>
 */

public class FreeGameRoundRuleContext extends RuleContext {

	private PlayRequest playRequest;
	private int playerFreeRounds;

	public FreeGameRoundRuleContext(PlayRequest playRequest, int playerFreeRounds) {
		this.playerFreeRounds = playerFreeRounds;
		this.playRequest = playRequest;
	}

	public int getPlayerFreeRounds() {
		return this.playerFreeRounds;
	}

	public PlayRequest getPlayRequest() {
		return this.playRequest;
	}

}
