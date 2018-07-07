package com.siddhrans.biometric.model;


import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
     
    @Column(name="FILE_TYPE", length=100, nullable=false)
    private String fileType;
    
    @Column(name="FILE_NAME", length=100, nullable=false)
    private String fileName;
    
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "BIOMETRIC_DATA_MACHINE", 
	joinColumns = { @JoinColumn(name = "BIOMETRIC_DATA_ID") }, 
	inverseJoinColumns = { @JoinColumn(name = "MACHINE_ID") })
	private BiometricMachine machine;
     
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
    
    public byte[] getContent() {
        return content;
    }
 
    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public BiometricMachine getMachine() {
		return machine;
	}

	public void setMachine(BiometricMachine machine) {
		this.machine = machine;
	}

	@Override
    public String toString() {
        return "BioMetric Data [id=" + id + ",Year=" + year + ", Month="
                + month + ", fileType=" + fileType + "]";
    }
}