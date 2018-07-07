package com.siddhrans.biometric.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="L_LEAVE_TYPES")
public class LeaveTypes {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="LEAVE_TYPE_ID")
	Integer leaveTypeId;
	
	@Column(name="LEAVE_TYPE", nullable=false)
	String leaveType;

	public Integer getLeaveTypeId() {
		return leaveTypeId;
	}

	public void setLeaveTypeId(Integer leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}	
}
