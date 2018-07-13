package com.siddhrans.biometric.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siddhrans.biometric.dao.ExamDao;
import com.siddhrans.biometric.model.Exams;
import com.siddhrans.biometric.service.ExamService;

@Service("examService")
@Transactional
public class ExamServiceImpl implements ExamService {

	@Autowired
    private ExamDao dao;

	@Override
	public Exams findById(int id) {
		return dao.findById(id);
	}

	@Override
	public void saveExam(Exams exam) {
		dao.saveExam(exam);
	}

	@Override
	public void updateExam(Exams exam) {
		dao.updateExam(exam);
	}

	@Override
	public void deleteExam(int examId) {
		dao.deleteExam(examId);
	}

	@Override
	public List<Exams> findAllExams() {
		return dao.findAllExams();
	}

	@Override
	public boolean isExamNameUnique(Integer id, String examName) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
