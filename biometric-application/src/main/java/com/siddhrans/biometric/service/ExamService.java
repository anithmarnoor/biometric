package com.siddhrans.biometric.service;

import java.util.List;

import com.siddhrans.biometric.model.Exams;
 
public interface ExamService {
     
    Exams findById(int id);

    void saveExam(Exams exam);
     
    void updateExam(Exams exam);
     
    void deleteExam(int examId);
 
    List<Exams> findAllExams(); 
     
    boolean isExamNameUnique(Integer id, String examName);
}