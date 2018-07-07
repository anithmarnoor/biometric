package com.siddhrans.biometric.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.siddhrans.biometric.dao.AbstractDao;
import com.siddhrans.biometric.dao.SalaryComponentFormulaDao;
import com.siddhrans.biometric.model.SalaryComponentFormula;

@Repository("salComponentFormulaDao")
public class SalaryComponentFormulaDaoImpl extends AbstractDao<Integer, SalaryComponentFormula> implements SalaryComponentFormulaDao {

	public void save(SalaryComponentFormula percentages) {
		//percentages.setId(1);//Only one row should be present. So setting it to 1
		super.persist(percentages);
	}

	public void update(SalaryComponentFormula percentages) {
		//percentages.setId(1);//Only one row should be present. Always update same. So setting it to 1
		super.update(percentages);
	}

	public List<SalaryComponentFormula> findSalaryDivPercentages() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<SalaryComponentFormula> percentages = (List<SalaryComponentFormula>) criteria.list();
		for(SalaryComponentFormula percentage:percentages)
		{
			Hibernate.initialize(percentage.getComponent());
		}
		return percentages;
	}

	@Override
	public SalaryComponentFormula findDivPercentageById(Integer id) {
		SalaryComponentFormula percentage = getByKey(id);
		if(percentage !=null){
			Hibernate.initialize(percentage.getComponent());
			return percentage;
		}
		return percentage;
	}

	/*public SalaryDivPercentages findSalDivPercentagesByDesignation(Designation designation) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("designation", designation));
		List<SalaryDivPercentages> dataList = (List<SalaryDivPercentages>)crit.list();
		SalaryDivPercentages percentage = dataList.get(0);
		Hibernate.initialize(percentage.getDivision());
		return percentage;
	}*/
}