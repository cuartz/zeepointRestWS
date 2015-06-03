/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.DAO;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author cuartz
 */
public interface DAO<T, ID extends Serializable> {

    T findById(ID id, boolean lock);

    List<T> findAll();

    List<T> findByExample(T exampleInstance, String[] excludeProperty);

    T makePersistent(T entity);

    void makeTransient(T entity);
}
