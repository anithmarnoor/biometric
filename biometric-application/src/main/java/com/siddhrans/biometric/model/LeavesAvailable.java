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
@Table(name="L_LEAVES_REMAINING")
public class LeavesAvailable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="REMAINING_LEAVES_ID")
	Integer availableLeavesId;
	
	@Column(name="REMAINING_LEAVES", nullable=false)
	Float availability;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "L_LEAVES_LEAVE_TYPE", 
             joinColumns = { @JoinColumn(name = "REMAINING_LEAVES_ID") }, 
             inverseJoinColumns = { @JoinColumn(name = "LEAVE_TYPE_ID")  })
	LeaveTypes leaveType;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "L_USER_LEAVES_REMAINING", 
             joinColumns = { @JoinColumn(name = "REMAINING_LEAVES_ID") }, 
             inverseJoinColumns = { @JoinColumn(name = "USER_ID")  })
	User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LeaveTypes getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveTypes leaveType) {
		this.leaveType = leaveType;
	}

	public Integer getAvailableLeavesId() {
		return availableLeavesId;
	}

	public void setAvailableLeavesId(Integer availableLeavesId) {
		this.availableLeavesId = availableLeavesId;
	}

	public Float getAvailability() {
		return availability;
	}

	public void setAvailability(Float availability) {
		this.availability = availability;
	}
}
