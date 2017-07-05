package com.siddhrans.biometric.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="WAGES")
public class Wages {
    @Id
    private Integer id;
 
    @Column(name="NORMAL_SHIFT", nullable=false)
    private String normalShift;
    
    @Column(name="PER_TRIP", nullable=false)
    private String perTrip;
     
    @Column(name="OVERTIME", nullable=false)
    private String overtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNormalShift() {
		return normalShift;
	}

	public void setNormalShift(String normalShift) {
		this.normalShift = normalShift;
	}

	public String getPerTrip() {
		return perTrip;
	}

	public void setPerTrip(String perTrip) {
		this.perTrip = perTrip;
	}

	public String getOvertime() {
		return overtime;
	}

	public void setOvertime(String overtime) {
		this.overtime = overtime;
	}
}
