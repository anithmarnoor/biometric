package com.siddhrans.biometric.model;

import java.io.Serializable;

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
@Table(name="PAYSLIP_PAYROLL_DETAILS")
public class UserPayRoll implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PAYROLL_ID")
	private Integer payRollId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "PAYSLIP_PD_PC", 
	joinColumns = { @JoinColumn(name = "PAYROLL_ID") }, 
	inverseJoinColumns = { @JoinColumn(name = "COMPONENT_ID") })
	private SalaryComponent component;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "PAYSLIP_PD_AU", 
	joinColumns = { @JoinColumn(name = "PAYROLL_ID") }, 
	inverseJoinColumns = { @JoinColumn(name = "User_ID") })
	private User user;

	@Column(name="COMPONENT_VALUE")
	private String componentValue;
	
	@Column(name="WAY_OF_DEFINING_VALUE")
	private String wayOfDefiningValue;

	public String getWayOfDefiningValue() {
		return wayOfDefiningValue;
	}

	public void setWayOfDefiningValue(String wayOfDefiningValue) {
		this.wayOfDefiningValue = wayOfDefiningValue;
	}

	public Integer getPayRollId() {
		return payRollId;
	}

	public void setPayRollId(Integer payRollId) {
		this.payRollId = payRollId;
	}

	public SalaryComponent getComponent() {
		return component;
	}

	public void setComponent(SalaryComponent component) {
		this.component = component;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getComponentValue() {
		return componentValue;
	}

	public void setComponentValue(String componentValue) {
		this.componentValue = componentValue;
	}

	@Override
	public String toString() {
		return "UserPayRoll Data [id=" + payRollId + ",Component=" + component + ", UserName="
				+ user + ", Value=" + componentValue + "]";
	}

}