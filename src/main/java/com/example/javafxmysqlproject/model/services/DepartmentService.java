package com.example.javafxmysqlproject.model.services;

import com.example.javafxmysqlproject.model.dao.DaoFactory;
import com.example.javafxmysqlproject.model.dao.GenericDao;
import com.example.javafxmysqlproject.model.dao.imp.DepartmentDaoImp;
import com.example.javafxmysqlproject.model.entities.Department;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DepartmentService {

    private GenericDao dao = DaoFactory.createDepartmentDao();

    public List<Department> findAll() {
        return dao.findAll();
    }

}
