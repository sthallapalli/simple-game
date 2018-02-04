package com.example.simplegame.domain;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author <a href="mailto:sthallapalli@outlook.com">sthallapalli</a>
 */

public class PlayResponse {

	private String roundId;
	private String playerId;
	private boolean isWin;
	private boolean offerFreeRound;

	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Instant timestamp;

	public PlayResponse() {
	}
	
	public PlayResponse(String roundId, String playerId) {
		this.roundId = roundId;
		this.playerId = playerId;
	}
	
	@JsonProperty("isWin")
	public boolean isWin() {
		return isWin;
	}

	public void setWin(boolean isWin) {
		this.isWin = isWin;
	}

	public boolean isOfferFreeRound() {
		return offerFreeRound;
	}

	public void setOfferFreeRound(boolean offerFreeRound) {
		this.offerFreeRound = offerFreeRound;
	}

	public String getRoundId() {
		return this.roundId;
	}
	
	public String getPlayerId() {
		return this.playerId;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "PlayRoundResponse [roundId=" + roundId + ", playerId=" + playerId + ", isWin=" + isWin
				+ ", offerFreeRound=" + offerFreeRound + ", timestamp=" + timestamp + "]";
	}

}
