package com.siddhrans.biometric.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="HOLIDAYS")
public class Holidays {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="HOLIDAY_ID")
	Integer holidayId;
	
	@NotEmpty
	@Column(name="HOLIDAY_DATE", unique=true,  nullable=false)
	Integer holidayDate;
	
	@Column(name="HOLIDAY_YEAR", nullable=false)
	Integer holidayYear;
	
	@Column(name="HOLIDAY_MONTH", nullable=false)
	Integer holidayMonth;
	
	@Column(name="HOLIDAY_REASON", nullable=false)
	String holidayReason;

	public Integer getHolidayId() {
		return holidayId;
	}

	public void setHolidayId(Integer holidayId) {
		this.holidayId = holidayId;
	}

	public String getHolidayReason() {
		return holidayReason;
	}

	public void setHolidayReason(String holidayReason) {
		this.holidayReason = holidayReason;
	}

	public Integer getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(Integer holidayDate) {
		this.holidayDate = holidayDate;
	}

	public Integer getHolidayYear() {
		return holidayYear;
	}

	public void setHolidayYear(Integer holidayYear) {
		this.holidayYear = holidayYear;
	}

	public Integer getHolidayMonth() {
		return holidayMonth;
	}

	public void setHolidayMonth(Integer holidayMonth) {
		this.holidayMonth = holidayMonth;
	}
}
