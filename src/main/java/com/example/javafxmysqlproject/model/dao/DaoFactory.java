package com.example.javafxmysqlproject.model.dao;

import com.example.javafxmysqlproject.db.DB;
import com.example.javafxmysqlproject.model.dao.imp.DepartmentDaoImp;
import com.example.javafxmysqlproject.model.dao.imp.SellerDaoImp;

public class DaoFactory {

    public static GenericDao createDepartmentDao() {
        return new DepartmentDaoImp(DB.getConnection());
    }

    public static GenericDao createSellerDao() {
        return new SellerDaoImp(DB.getConnection());
    }


}
