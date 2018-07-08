package com.siddhrans.biometric.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.siddhrans.biometric.dao.AbstractDao;
import com.siddhrans.biometric.dao.HolidaysDao;
import com.siddhrans.biometric.model.Holidays;

@Repository("holidaysDao")
@Transactional
public class HolidaysDaoImpl extends AbstractDao<Integer, Holidays> implements HolidaysDao {

	@Override
	public List<Holidays> findAllHolidays() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Holidays> holidays = (List<Holidays>) criteria.list();
		return holidays;
	}

	@Override
	public Holidays findHolidaysById(int holidaysId) {
		return getByKey(holidaysId);
	}

	@Override
	public List<Holidays> findHolidaysByYear(int year) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		criteria.add(Restrictions.eq("holidayYear", year));
		List<Holidays> holidaysList = (List<Holidays>) criteria.list();
		if(holidaysList.size() >0){
			return holidaysList;
		}
		return null;
	}
	
	@Override
	public List<Holidays> findHolidaysByMonthAndYear(int month, int year) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		criteria.add(Restrictions.eq("holidayYear", year));
		criteria.add(Restrictions.eq("holidayMonth", month));
		List<Holidays> holidaysList = (List<Holidays>) criteria.list();
		if(holidaysList.size() >0){
			return holidaysList;
		}
		return null;
	}
	
	@Override
	public List<Holidays> findHolidaysByMonthAndYearAndDate(int month, int year, int date) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		criteria.add(Restrictions.eq("holidayYear", year));
		criteria.add(Restrictions.eq("holidayMonth", month));
		criteria.add(Restrictions.eq("holidayDate", date));
		List<Holidays> holidaysList = (List<Holidays>) criteria.list();
		if(holidaysList.size() >0){
			return holidaysList;
		}
		return null;
	}
	
	@Override
	public List<Holidays> findHolidaysByDate(String date) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		criteria.add(Restrictions.eq("holidayDate", Integer.parseInt(date)));
		List<Holidays> holidaysList = (List<Holidays>) criteria.list();
		if(holidaysList.size() >0){
			return holidaysList;
		}
		return null;
	}
	
	@Override
	public List<Holidays> findHolidaysByMonth(int month) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		criteria.add(Restrictions.eq("holidayMonth", month));
		List<Holidays> holidaysList = (List<Holidays>) criteria.list();
		if(holidaysList.size() >0){
			return holidaysList;
		}
		return null;
	}

	@Override
	public void saveHolidays(Holidays holidays) {
		persist(holidays);
	}

	@Override
	public void updateHolidays(Holidays holidays) {
		update(holidays);
	}

	@Override
	public void deleteHolidays(Holidays holidays) {
		delete(holidays);
	}	
}
