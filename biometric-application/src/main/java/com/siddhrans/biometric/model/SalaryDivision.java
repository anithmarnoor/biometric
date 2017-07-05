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
    private Float basicPercentage;
    
    @Column(name="CONVEYANCE_PERCENT")
    private Float conveyancePercentage;
    
    @Column(name="HRA_PERCENT")
    private Float hraPercentage;
    
    @Column(name="LTA_PERCENT")
    private Float ltaPercentage;
    
    @Column(name="MED_R_PERCENT")
    private Float mrPercentage;
    
    @Column(name="ESI_PERCENT")
    private Float esiPercentage;
    
    @Column(name="SA_PERCENT")
    private Float saPercentage;
    
    @Column(name="TAX_PERCENT")
    private Float incomeTaxPercentage;
    
    @Column(name="PF_PERCENT")
    private Float pfPercentage;
    
    @Column(name="PT_PERCENT")
    private Float ptPercentage;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getBasicPercentage() {
		return basicPercentage;
	}

	public void setBasicPercentage(Float basicPercentage) {
		this.basicPercentage = basicPercentage;
	}

	public Float getConveyancePercentage() {
		return conveyancePercentage;
	}

	public void setConveyancePercentage(Float conveyancePercentage) {
		this.conveyancePercentage = conveyancePercentage;
	}

	public Float getHraPercentage() {
		return hraPercentage;
	}

	public void setHraPercentage(Float hraPercentage) {
		this.hraPercentage = hraPercentage;
	}

	public Float getLtaPercentage() {
		return ltaPercentage;
	}

	public void setLtaPercentage(Float ltaPercentage) {
		this.ltaPercentage = ltaPercentage;
	}

	public Float getMrPercentage() {
		return mrPercentage;
	}

	public void setMrPercentage(Float mrPercentage) {
		this.mrPercentage = mrPercentage;
	}

	public Float getEsiPercentage() {
		return esiPercentage;
	}

	public void setEsiPercentage(Float esiPercentage) {
		this.esiPercentage = esiPercentage;
	}

	public Float getSaPercentage() {
		return saPercentage;
	}

	public void setSaPercentage(Float saPercentage) {
		this.saPercentage = saPercentage;
	}

	public Float getIncomeTaxPercentage() {
		return incomeTaxPercentage;
	}

	public void setIncomeTaxPercentage(Float incomeTaxPercentage) {
		this.incomeTaxPercentage = incomeTaxPercentage;
	}

	public Float getPfPercentage() {
		return pfPercentage;
	}

	public void setPfPercentage(Float pfPercentage) {
		this.pfPercentage = pfPercentage;
	}

	public Float getPtPercentage() {
		return ptPercentage;
	}

	public void setPtPercentage(Float ptPercentage) {
		this.ptPercentage = ptPercentage;
	}
}