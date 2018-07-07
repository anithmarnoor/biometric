package com.siddhrans.biometric.model;

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
@Table(name="WAGES")
public class Wages {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="WAGES_ID", nullable=true)
    private Integer id;
 
    @Column(name="NORMAL_SHIFT", nullable=true)
    private String normalShift;
    
    @Column(name="CTC", nullable=true)
    private String ctc;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_WAGES", 
             joinColumns = { @JoinColumn(name = "WAGES_ID") }, 
             inverseJoinColumns = { @JoinColumn(name = "USER_ID", unique= true)  })
	User user;
 
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCtc() {
		return ctc;
	}

	public void setCtc(String ctc) {
		this.ctc = ctc;
	}

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
}
