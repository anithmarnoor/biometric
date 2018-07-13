package com.siddhrans.biometric.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.siddhrans.biometric.dao.AbstractDao;
import com.siddhrans.biometric.dao.ExamDao;
import com.siddhrans.biometric.model.Exams;

@Repository("examDao")
@Transactional
public class ExamDaoImpl extends AbstractDao<Integer, Exams> implements ExamDao {

	@Override
	public Exams findById(int id) {
		Exams Exams = getByKey(id);
		return Exams;
	}

	@Override
	public void saveExam(Exams Exams) {
		persist(Exams);
	}

	@Override
	public void updateExam(Exams Exams) {
		update(Exams);
	}
	
	@Override
	public void deleteExam(int id) {
		Exams Exams = findById(id);
		delete(Exams);
	}

	@Override
	public List<Exams> findAllExams() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("examId"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Exams> examsList = (List<Exams>) criteria.list();
		return examsList;
	}

	@Override
	public boolean isExamNameUnique(Integer id, String examName) {
		// TODO Auto-generated method stub
		return false;
	}

}
