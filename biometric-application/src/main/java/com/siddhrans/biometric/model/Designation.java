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
@Table(name="DESIGNATIONS")
public class Designation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="DESIGNATION_ID")
	Integer designationId;
	
	@Column(name="DESIGNATION_NAME", nullable=false)
	String designationName;
		
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "DEPARTMENT_DESIGNATION", 
             joinColumns = { @JoinColumn(name = "DESIGNATION_ID") }, 
             inverseJoinColumns = { @JoinColumn(name = "DEPARTMENT_ID") })
	Department department;

	public Integer getDesignationId() {
		return designationId;
	}

	public void setDesignationId(Integer designationId) {
		this.designationId = designationId;
	}

	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}
