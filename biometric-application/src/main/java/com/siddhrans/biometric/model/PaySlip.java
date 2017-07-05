package com.siddhrans.biometric.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="PAY_DETAILS")
public class PaySlip implements Serializable{
 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="USER_ID")
    private Integer userId;

	@Column(name="BASIC")
    private Float basic;
    
    @Column(name="CONVEYANCE")
    private Float conveyance;
    
    @Column(name="HRA")
    private Float hra;	
    
    @Column(name="LTA")
    private Float lta;
    
    @Column(name="MED_R")
    private Float mr;
    
    @Column(name="ESI")
    private Float esi;
    
    @Column(name="SA")
    private Float sa;
    
    @Column(name="TAX")
    private Float incomeTax;
    
    @Column(name="PF")
    private Float pf;
    
    @Column(name="PT")
    private Float pt;
   
    @Column(name="ATTENDANCE")
    private Float attendance;
    
    @Column(name="TOTAL_SALARY")
    private Float totalSalary;
    
    @Column(name="OVER_TIME_AMOUNT")
    private Float overTimeAmount;
    
    @Column(name="OVER_TIME_HOURS")
    private Float overTimeHours;
    
    @Column(name="PAN")
    private String panNo;
    
    @Column(name="MONTH")
    private Integer month;

    @Column(name="YEAR")
    private Integer year;
    
    public Float getTotalSalary() {
		return totalSalary;
	}

	public void setTotalSalary(Float totalSalary) {
		this.totalSalary = totalSalary;
	}

	public Float getOverTimeAmount() {
		return overTimeAmount;
	}

	public void setOverTimeAmount(Float overTimeAmount) {
		this.overTimeAmount = overTimeAmount;
	}

	public Float getOverTimeHours() {
		return overTimeHours;
	}

	public void setOverTimeHours(Float overTimeHours) {
		this.overTimeHours = overTimeHours;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
    
	public Float getAttendance() {
		return attendance;
	}

	public void setAttendance(Float attendance) {
		this.attendance = attendance;
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

	public Float getBasic() {
		return basic;
	}

	public void setBasic(Float basic) {
		this.basic = basic;
	}

	public Float getConveyance() {
		return conveyance;
	}

	public void setConveyance(Float conveyance) {
		this.conveyance = conveyance;
	}

	public Float getHra() {
		return hra;
	}

	public void setHra(Float hra) {
		this.hra = hra;
	}

	public Float getLta() {
		return lta;
	}

	public void setLta(Float lta) {
		this.lta = lta;
	}

	public Float getMr() {
		return mr;
	}

	public void setMr(Float mr) {
		this.mr = mr;
	}

	public Float getEsi() {
		return esi;
	}

	public void setEsi(Float esi) {
		this.esi = esi;
	}

	public Float getSa() {
		return sa;
	}

	public void setSa(Float sa) {
		this.sa = sa;
	}

	public Float getIncomeTax() {
		return incomeTax;
	}

	public void setIncomeTax(Float incomeTax) {
		this.incomeTax = incomeTax;
	}

	public Float getPf() {
		return pf;
	}

	public void setPf(Float pf) {
		this.pf = pf;
	}

	public Float getPt() {
		return pt;
	}

	public void setPt(Float pt) {
		this.pt = pt;
	}
}