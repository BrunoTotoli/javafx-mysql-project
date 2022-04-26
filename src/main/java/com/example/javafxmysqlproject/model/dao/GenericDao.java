package com.example.javafxmysqlproject.model.dao;

import com.example.javafxmysqlproject.model.entities.Department;

import java.util.List;

public interface GenericDao<T> {

    void insert(T t);

    void deleteById(Integer id);

    void update(T t);

    T findById(Integer id);

    List<T> findAll();


}
