package com.example.simplegame.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author <a href="mailto:sthallapalli@outlook.com">sthallapalli</a>
 */

@Entity
@Table(name = "ROUND_INFO", schema = "GAME_SCHEMA")
public class RoundInfo {

	@Id
	@Column(name = "ID", unique = true)
	private String roundId;
	
	@Column(name = "PLAYER_NAME")
	private String playerId;
	
	@Column(name = "IS_WON")
	private boolean isWon;
	
	@Column(name = "IS_FREE_ROUND")
	private boolean isFreeRound;
	
	@Embedded
	private AuditInfo audit;

	public String getRoundId() {
		return roundId;
	}

	public void setRoundId(String roundId) {
		this.roundId = roundId;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public boolean isWon() {
		return isWon;
	}

	public void setWon(boolean isWon) {
		this.isWon = isWon;
	}

	public boolean isFreeRound() {
		return isFreeRound;
	}

	public void setFreeRound(boolean isFreeRound) {
		this.isFreeRound = isFreeRound;
	}

	public AuditInfo getAudit() {
		return audit;
	}

	public void setAudit(AuditInfo audit) {
		this.audit = audit;
	}

	@Override
	public String toString() {
		return "RoundInfo [roundId=" + roundId + ", playerId=" + playerId + ", isWon=" + isWon + ", isFreeRound="
				+ isFreeRound + ", audit=" + audit + "]";
	}

}
