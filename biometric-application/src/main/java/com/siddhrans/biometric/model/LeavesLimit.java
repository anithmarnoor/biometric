package com.siddhrans.biometric.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="L_LEAVES_LIMIT")
public class LeavesLimit {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="LEAVES_LIMIT_ID")
	Integer leavesLimitId;
	
	@Column(name="LEAVES_LIMIT", nullable=false)
	String limit;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "L_LEAVES_TYPE_LIMIT", 
             joinColumns = { @JoinColumn(name = "LEAVES_LIMIT_ID") }, 
             inverseJoinColumns = { @JoinColumn(name = "LEAVE_TYPE_ID")  })
	LeaveTypes leaveType;
	
	public LeaveTypes getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveTypes leaveType) {
		this.leaveType = leaveType;
	}

	public Integer getLeavesLimitId() {
		return leavesLimitId;
	}

	public void setLeavesLimitId(Integer leavesLimitId) {
		this.leavesLimitId = leavesLimitId;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}
}
