package com.siddhrans.biometric.model;


import java.io.Serializable;
import java.util.Date;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

 
@Entity
@Table(name="BIOMETRIC_DATA")
public class BiometricData implements Serializable{
 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="BIOMETRIC_DATA_ID")
    private Integer id; 
     
    @Column(name="YEAR", nullable=false)
    private String year;
     
    @Column(name="MONTH", nullable=false)
    private String month;
     
    @Column(name="type", length=100, nullable=false)
    private String type;
    
    @Column(name="name", length=100, nullable=false)
    private String name;
     
    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name="content", nullable=false)
    private byte[] content;
    
    
    @Transient
    MultipartFile file;
    
    @Transient
	boolean isMultipleShift;
	
	public boolean isMultipleShift() {
		return isMultipleShift;
	}

	public void setMultipleShift(boolean isMultipleShift) {
		this.isMultipleShift = isMultipleShift;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
    
    public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }
 
    public String getType() {
        return type;
    }
 
    public void setType(String type) {
        this.type = type;
    }
 
    public byte[] getContent() {
        return content;
    }
 
    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "BioMetric Data [id=" + id + ",Year=" + year + ", Month="
                + month + ", type=" + type + "]";
    }
}