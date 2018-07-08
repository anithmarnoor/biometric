package com.siddhrans.biometric.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="L_USER_LEAVES")
public class Leaves {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="LEAVE_ID")
	Integer leaveId;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "L_USER_LEAVE_TYPES", 
             joinColumns = { @JoinColumn(name = "LEAVE_ID") }, 
             inverseJoinColumns = { @JoinColumn(name = "LEAVE_TYPE_ID")  })
	LeaveTypes leaveTypes;
	
	@Column(name="START_DATE", nullable=false)
    private String startDate;
	
	@Column(name="END_DATE", nullable=false)
    private String endDate;
	
	@Column(name="COMMENT", nullable=false)
    private String comment;
	
	@Column(name="NO_OF_DAYS")
    private String noOfDays;
	
	@Column(name="LEAVE_STATUS")
    private String leaveStatus;
	
	@Column(name="FULL_OR_HALF")
    private String fullOrHalf;
	
	public String getFullOrHalf() {
		return fullOrHalf;
	}

	public void setFullOrHalf(String fullOrHalf) {
		this.fullOrHalf = fullOrHalf;
	}

	public String getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	@OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "LEAVE_USER", 
             joinColumns = { @JoinColumn(name = "LEAVE_ID") }, 
             inverseJoinColumns = { @JoinColumn(name = "USER_ID")  })
	User user;

	public Integer getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
	}

	public LeaveTypes getLeaveTypes() {
		return leaveTypes;
	}

	public void setLeaveTypes(LeaveTypes leaveTypes) {
		this.leaveTypes = leaveTypes;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(String noOfDays) {
		this.noOfDays = noOfDays;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
