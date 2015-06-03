/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.DAO;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import org.hibernate.LockOptions;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
/**
 *
 * @author cuartz
 * @param <T>
 * @param <ID>
 */
//@Component
public abstract class DAOImpl<T, ID extends Serializable>  
        implements DAO<T, ID> {  
  
    private final Class<T> persistentClass;  
    
    @Autowired
    SessionFactory sessionFactory;
  
    public DAOImpl() {  
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()  
                                .getGenericSuperclass()).getActualTypeArguments()[0];  
     }  
 
    protected Session getSession() {   
        return sessionFactory.getCurrentSession();  
    }  
  
    public Class<T> getPersistentClass() {  
        return persistentClass;  
    }  
  
    /**
     *
     * @param id
     * @param lock
     * @return
     */
    @SuppressWarnings("unchecked")  
    @Override
    public T findById(ID id, boolean lock) {  
        T entity;  
        if (lock)  
            entity = (T) getSession().load(getPersistentClass(), id, LockOptions.UPGRADE);  
        else  
            entity = (T) getSession().load(getPersistentClass(), id);  
  
        return entity;  
    }  
  
    /**
     *
     * @return
     */
    @SuppressWarnings("unchecked")  
    @Override
    public List<T> findAll() {  
        return findByCriteria();  
    }  
  
    /**
     *
     * @param exampleInstance
     * @param excludeProperty
     * @return
     */
    @SuppressWarnings("unchecked")  
    @Override
    public List<T> findByExample(T exampleInstance, String[] excludeProperty) {  
        Criteria crit = getSession().createCriteria(getPersistentClass());  
        Example example =  Example.create(exampleInstance);  
        for (String exclude : excludeProperty) {  
            example.excludeProperty(exclude);  
        }  
        crit.add(example);  
        return crit.list();  
    }  
  
    /**
     *
     * @param entity
     * @return
     */
    @SuppressWarnings("unchecked")  
    @Override
    public T makePersistent(T entity) {  
        getSession().saveOrUpdate(entity);  
        return entity;  
    }  
  
    /**
     *
     * @param entity
     */
    @Override
    public void makeTransient(T entity) {  
        getSession().delete(entity);  
    }  
  
    public void flush() {  
        getSession().flush();  
    }  
  
    public void clear() {  
        getSession().clear();  
    }  
  
    /** 
     * Use this inside subclasses as a convenience method. 
     * @param criterion
     * @return 
     */  
    @SuppressWarnings("unchecked")  
    protected List<T> findByCriteria(Criterion... criterion) {  
        Criteria crit = getSession().createCriteria(getPersistentClass());  
        for (Criterion c : criterion) {  
            crit.add(c);  
        }  
        return crit.list();  
   }  
  
}  