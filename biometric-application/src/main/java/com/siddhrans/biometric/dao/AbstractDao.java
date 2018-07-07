package com.siddhrans.biometric.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
 
public abstract class AbstractDao<PK extends Serializable, T> {
     
    private final Class<T> persistentClass;
     
    @SuppressWarnings("unchecked")
    public AbstractDao(){
        this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }
     
    @Autowired
    private SessionFactory sessionFactory;
    
    /*@Autowired
    private HibernateTemplate hibernateTemplate;*/
     
    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }
 
    @SuppressWarnings("unchecked")
    public T getByKey(PK key) {
        return (T) getSession().get(persistentClass, key);
    }
 
    public void persist(T entity) {
        /*getSession().persist(entity);*/
        getSession().saveOrUpdate(entity);
    }
 
    public void update(T entity) {
        getSession().update(entity);
    }
 
    public void delete(T entity) {
        getSession().delete(entity);
    }
    
    public List<T> getAll(T entity) {
        return getSession().createQuery("from "+entity).list();
    }
    
   /* public List<T> saveOrUpdateAll(T entity) {
    	hibernateTemplate.bulkUpdate("", new ArrayList());
        return getSession().createQuery("from "+entity).list();
    }*/
     
    protected Criteria createEntityCriteria(){
        return getSession().createCriteria(persistentClass);
    }
 
}
