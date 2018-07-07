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
import javax.persistence.Table;

@Entity
@Table(name="OVERTIME")
public class OverTime {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="OVERTIME_ID")
	Integer overTimeId;
	
	@Column(name="OVERTIME_AMOUNT", nullable=false)
	String overtimeAmount;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "DESIGNATION_OVERTIME", 
             joinColumns = { @JoinColumn(name = "OVERTIME_ID") }, 
             inverseJoinColumns = { @JoinColumn(name = "DESIGNATION_ID", unique= true)  })
	Designation designation;

	public Integer getOverTimeId() {
		return overTimeId;
	}

	public void setOverTimeId(Integer overTimeId) {
		this.overTimeId = overTimeId;
	}

	public String getOvertimeAmount() {
		return overtimeAmount;
	}

	public void setOvertimeAmount(String overtimeAmount) {
		this.overtimeAmount = overtimeAmount;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}
}
