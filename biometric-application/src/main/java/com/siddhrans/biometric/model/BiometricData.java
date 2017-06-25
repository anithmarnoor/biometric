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
 
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id; 
     
    @Column(name="START_DATE", nullable=false)
    private Date startDate;
     
    @Column(name="END_DATE", nullable=false)
    private Date endDate;
     
    @Column(name="type", length=100, nullable=false)
    private String type;
    
    @Column(name="name", length=100, nullable=false)
    private String name;
     
    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name="content", nullable=false)
    private byte[] content;
    
    
    @Transient
    MultipartFile file;
    
    
    public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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


/*	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        return result;
    }*/
 
   /* @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof BiometricData))
            return false;
        BiometricData other = (BiometricData) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        if (endDate == null) {
            if (other.endDate != null)
                return false;
        } else if (!endDate.equals(other.endDate))
            return false;
        return true;
    }
 */
    @Override
    public String toString() {
        return "BioMetric Data [id=" + id + ", Start Date=" + startDate + ", End Date="
                + endDate + ", type=" + type + "]";
    }
}