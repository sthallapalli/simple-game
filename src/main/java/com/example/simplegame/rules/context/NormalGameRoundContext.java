package com.example.simplegame.rules.context;

import com.example.simplegame.framework.rules.RuleContext;

/**
 * @author <a href="mailto:sthallapalli@outlook.com">sthallapalli</a>
 */

public class NormalGameRoundContext extends RuleContext {

	private int coins;
	private boolean isFreeRound;
	
	public NormalGameRoundContext(int wager, boolean isFreeRound) {
		this.coins = wager;
		this.isFreeRound = isFreeRound;
	}
	
	public int getCoins() {
		return this.coins;
	}

	public boolean isFreeRound() {
		return this.isFreeRound;
	}
}
