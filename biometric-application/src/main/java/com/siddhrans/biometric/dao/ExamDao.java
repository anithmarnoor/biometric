package com.siddhrans.biometric.dao;

import java.util.List;

import com.siddhrans.biometric.model.Exams;
 
public interface ExamDao {
    
   Exams findById(int id);

   void saveExam(Exams exam);
    
   void updateExam(Exams exam);
    
   void deleteExam(int examId);

   List<Exams> findAllExams(); 
    
   boolean isExamNameUnique(Integer id, String examName);
}