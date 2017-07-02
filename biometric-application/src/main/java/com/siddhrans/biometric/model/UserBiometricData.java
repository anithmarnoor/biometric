package com.siddhrans.biometric.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name="USER_BIOMETRIC_DATA")
public class UserBiometricData implements Serializable{
 
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="BIOMETRIC_DATA_ID")
    private Integer id; 
     
    @Column(name="MONTH", length=100, nullable=false)
    private Integer month;
     
    @Column(name="YEAR", length=255)
    private Integer year;
    
    @Column(name="USER_ID")
    private Integer userId;

    @Column(name="DATE", length=255)
	private Integer date;
    
    @Column(name="LOGOUT_TIME", length=255)
	private String logoutTime;
    
    @Column(name="LOGIN_TIME", length=255)
    private String loginTime;
    
    @Column(name="NO_OF_HOURS", length=255)
    private String noOfHours;
    
    @Column(name="NO_OF_MINS", length=255)
    private String noOfMins;
   
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
		this.date = date;
	}

	public String getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getNoOfHours() {
		return noOfHours;
	}

	public void setNoOfHours(String noOfHours) {
		this.noOfHours = noOfHours;
	}

	public String getNoOfMins() {
		return noOfMins;
	}

	public void setNoOfMins(String noOfMins) {
		this.noOfMins = noOfMins;
	}
 
	public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
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

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((month == null) ? 0 : month.hashCode());
        return result;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof BiometricData))
            return false;
        UserBiometricData other = (UserBiometricData) obj;
        if (id == null) {
            if (other.year != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (month == null) {
            if (other.month != null)
                return false;
        } else if (!month.equals(other.month))
            return false;
        return true;
    }
 
    @Override
    public String toString() {
        return "BioMetric Data [id=" + id + ", Month=" + month + ", Year="
                + year + "]";
    }
}