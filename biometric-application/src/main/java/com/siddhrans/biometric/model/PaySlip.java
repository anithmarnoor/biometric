package com.siddhrans.biometric.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="PAY_SLIP")
public class PaySlip implements Serializable{
 
    @Id
    private Integer id;
    
    @Column(name="BASIC")
    private Integer basic;
    
    @Column(name="CONVEYANCE")
    private Integer conveyance;
    
    @Column(name="HRA")
    private Integer hra;
    
    @Column(name="LTA")
    private Integer lta;
    
    @Column(name="MED_R")
    private Integer mr;
    
    @Column(name="ESI")
    private Integer esi;
    
    @Column(name="SA")
    private Integer sa;
    
    @Column(name="TAX")
    private Integer incomeTax;
    
    @Column(name="PF")
    private Integer pf;
    
    @Column(name="PT")
    private Integer pt;
   
    @Column(name="ATTENDANCE")
    private Float attendance;
    
    @Column(name="OVER_TIME")
    private Float overTime;
    
    @Column(name="PAN")
    private String panNo;
    
    @Column(name="month")
    private Integer month;

    @Column(name="Year")
    private Integer year;
    	
    
	public Float getAttendance() {
		return attendance;
	}

	public void setAttendance(Float attendance) {
		this.attendance = attendance;
	}

	public Float getOverTime() {
		return overTime;
	}

	public void setOverTime(Float overTime) {
		this.overTime = overTime;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBasic() {
		return basic;
	}

	public void setBasic(Integer basic) {
		this.basic = basic;
	}

	public Integer getConveyance() {
		return conveyance;
	}

	public void setConveyance(Integer conveyance) {
		this.conveyance = conveyance;
	}

	public Integer getHra() {
		return hra;
	}

	public void setHra(Integer hra) {
		this.hra = hra;
	}

	public Integer getLta() {
		return lta;
	}

	public void setLta(Integer lta) {
		this.lta = lta;
	}

	public Integer getMr() {
		return mr;
	}

	public void setMr(Integer mr) {
		this.mr = mr;
	}

	public Integer getEsi() {
		return esi;
	}

	public void setEsi(Integer esi) {
		this.esi = esi;
	}

	public Integer getSa() {
		return sa;
	}

	public void setSa(Integer sa) {
		this.sa = sa;
	}

	public Integer getIncomeTax() {
		return incomeTax;
	}

	public void setIncomeTax(Integer incomeTax) {
		this.incomeTax = incomeTax;
	}

	public Integer getPf() {
		return pf;
	}

	public void setPf(Integer pf) {
		this.pf = pf;
	}

	public Integer getPt() {
		return pt;
	}

	public void setPt(Integer pt) {
		this.pt = pt;
	}
    
}