package com.siddhrans.biometric.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="SALARY_DIVISION")
public class SalaryDivision implements Serializable{
 
    @Id
    private Integer id;
    
    @Column(name="BASIC_PERCENT")
    private Integer basicPercentage;
    
    @Column(name="CONVEYANCE_PERCENT")
    private Integer conveyancePercentage;
    
    @Column(name="HRA_PERCENT")
    private Integer hraPercentage;
    
    @Column(name="LTA_PERCENT")
    private Integer ltaPercentage;
    
    @Column(name="MED_R_PERCENT")
    private Integer mrPercentage;
    
    @Column(name="ESI_PERCENT")
    private Integer esiPercentage;
    
    @Column(name="SA_PERCENT")
    private Integer saPercentage;
    
    @Column(name="TAX_PERCENT")
    private Integer incomeTaxPercentage;
    
    @Column(name="PF_PERCENT")
    private Integer pfPercentage;
    
    @Column(name="PT_PERCENT")
    private Integer ptPercentage;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBasicPercentage() {
		return basicPercentage;
	}

	public void setBasicPercentage(Integer basicPercentage) {
		this.basicPercentage = basicPercentage;
	}

	public Integer getConveyancePercentage() {
		return conveyancePercentage;
	}

	public void setConveyancePercentage(Integer conveyancePercentage) {
		this.conveyancePercentage = conveyancePercentage;
	}

	public Integer getHraPercentage() {
		return hraPercentage;
	}

	public void setHraPercentage(Integer hraPercentage) {
		this.hraPercentage = hraPercentage;
	}

	public Integer getLtaPercentage() {
		return ltaPercentage;
	}

	public void setLtaPercentage(Integer ltaPercentage) {
		this.ltaPercentage = ltaPercentage;
	}

	public Integer getMrPercentage() {
		return mrPercentage;
	}

	public void setMrPercentage(Integer mrPercentage) {
		this.mrPercentage = mrPercentage;
	}

	public Integer getEsiPercentage() {
		return esiPercentage;
	}

	public void setEsiPercentage(Integer esiPercentage) {
		this.esiPercentage = esiPercentage;
	}

	public Integer getSaPercentage() {
		return saPercentage;
	}

	public void setSaPercentage(Integer saPercentage) {
		this.saPercentage = saPercentage;
	}

	public Integer getIncomeTaxPercentage() {
		return incomeTaxPercentage;
	}

	public void setIncomeTaxPercentage(Integer incomeTaxPercentage) {
		this.incomeTaxPercentage = incomeTaxPercentage;
	}

	public Integer getPfPercentage() {
		return pfPercentage;
	}

	public void setPfPercentage(Integer pfPercentage) {
		this.pfPercentage = pfPercentage;
	}

	public Integer getPtPercentage() {
		return ptPercentage;
	}

	public void setPtPercentage(Integer ptPercentage) {
		this.ptPercentage = ptPercentage;
	}
    
}