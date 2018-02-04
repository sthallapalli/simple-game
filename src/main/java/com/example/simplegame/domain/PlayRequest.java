package com.example.simplegame.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author <a href="mailto:sthallapalli@outlook.com">sthallapalli</a>
 */

public class PlayRequest {

	private String playerId;
	private int coins;

	@JsonProperty("isFreeRound")
	private boolean isFreeRound = false;

	
	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public boolean isFreeRound() {
		return isFreeRound;
	}

	public void setFreeRound(boolean isFreeRound) {
		this.isFreeRound = isFreeRound;
	}

	@Override
	public String toString() {
		return "BetInfo [playerId=" + playerId + ", coins=" + coins + ", isFreeRound=" + isFreeRound + "]";
	}

	
	
}
