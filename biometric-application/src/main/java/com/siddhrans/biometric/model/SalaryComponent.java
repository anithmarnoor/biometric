package com.siddhrans.biometric.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PAYSLIP_COMPONENT")
public class SalaryComponent implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="COMPONENT_ID")
	private Integer componentId;

	@Column(name="COMPONENT_NAME", nullable=false, unique=true)
	private String componentName;

	@Column(name="IS_DEDUCTIBLE", nullable=false)
	private String isDeductible;

	public String getIsDeductible() {
		return isDeductible;
	}

	public void setIsDeductible(String isDeductible) {
		this.isDeductible = isDeductible;
	}

	public Integer getComponentId() {
		return componentId;
	}

	public void setComponentId(Integer componentId) {
		this.componentId = componentId;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	@Override
	public String toString() {
		return "Component Data [id=" + componentId + ",Component=" + componentName + ", isDeductible="
				+ isDeductible + "]";
	}

}