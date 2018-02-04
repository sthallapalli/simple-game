package com.example.simplegame.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;
/**
 * @author <a href="mailto:sthallapalli@outlook.com">sthallapalli</a>
 */

@Embeddable
public class AuditInfo {

	@Column(name = "CREATE_TIME")
	private Timestamp createTime;

	@Column(name = "UPDATE_TIME")
	private Timestamp updateTime;

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "Audit [createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

}
