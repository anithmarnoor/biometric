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
@Table(name="PAYSLIP_DETAILS_USER")
public class PaySlip implements Serializable{
 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="PAY_SLIP_ID")
    private Integer id;
    
    @Column(name="COMPONENT_NAME")
    private String componentName;
    
    @Column(name="VALUE")
    private Float value;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "PAYSLIP_PDU_AU", 
             joinColumns = { @JoinColumn(name = "PAY_SLIP_ID") }, 
             inverseJoinColumns = { @JoinColumn(name = "USER_ID")  })
	User user;
    
    @Column(name="MONTH")
    private Integer month;

    @Column(name="YEAR")
    private Integer year;
    
    @Column(name="ATTENDANCE")
    private Float attendance;
    
    @Column(name="OVER_TIME_HOURS")
    private Float overTimeHours;

	public Float getAttendance() {
		return attendance;
	}

	public void setAttendance(Float attendance) {
		this.attendance = attendance;
	}

	public Float getOverTimeHours() {
		return overTimeHours;
	}

	public void setOverTimeHours(Float overTimeHours) {
		this.overTimeHours = overTimeHours;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	@Override
    public String toString() {
        return "User [id=" + id + ", componentName=" + componentName + ", value=" + value
                + ", userId=" + user.getId() + ", month=" + month
                + ", year=" + year + "]";
    }
}