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

@SuppressWarnings("serial")
@Entity
@Table(name="PAYSLIP_COMPONENT_FORMULA")
public class SalaryComponentFormula implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="FORMULA_ID")
	private Integer formulaId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "PAYSLIP_CF_PC", 
	joinColumns = { @JoinColumn(name = "FORMULA_ID") }, 
	inverseJoinColumns = { @JoinColumn(name = "COMPONENT_ID") })
	private SalaryComponent component;

	@Column(name="formula")
	private String formula;

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public Integer getFormulaId() {
		return formulaId;
	}

	public void setFormulaId(Integer percentageId) {
		this.formulaId = percentageId;
	}

	public SalaryComponent getComponent() {
		return component;
	}

	public void setComponent(SalaryComponent component) {
		this.component = component;
	}

}