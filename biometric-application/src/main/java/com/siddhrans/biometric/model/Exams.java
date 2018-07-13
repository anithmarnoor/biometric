package com.siddhrans.biometric.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Exams")
public class Exams implements Serializable{
 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="EXAM_ID")
    private Integer examId;
    
    @Column(name="EXAM_NAME")
    private String examName;

	public Integer getExamId() {
		return examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

}