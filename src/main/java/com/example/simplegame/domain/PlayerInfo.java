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
@Table(name = "PLAYER", schema = "GAME_SCHEMA")
public class PlayerInfo {
	
	@Id
	@Column(name = "USER_ID", unique = true)
	private String playerId;
	
	@Column(name = "TOTAL_COINS")
	private Double totalMoney;
	
	@Column(name = "WIN_COINS")
	private Double winMoney;
	
	@Column(name = "FREE_ROUNDS")
	private int freeRounds;

	@Embedded
	private AuditInfo audit;
	
	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public Double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Double getWinMoney() {
		return winMoney;
	}

	public void setWinMoney(Double winMoney) {
		this.winMoney = winMoney;
	}

	public int getFreeRounds() {
		return freeRounds;
	}

	public void setFreeRounds(int freeRounds) {
		this.freeRounds = freeRounds;
	}

	public AuditInfo getAudit() {
		return audit;
	}

	public void setAudit(AuditInfo audit) {
		this.audit = audit;
	}

	@Override
	public String toString() {
		return "PlayerInfo [playerName=" + playerId + ", totalMoney=" + totalMoney + ", winMoney=" + winMoney
				+ ", freeRounds=" + freeRounds + ", audit=" + audit + "]";
	}
}
