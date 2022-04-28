package com.example.javafxmysqlproject.model.services;

import com.example.javafxmysqlproject.model.dao.DaoFactory;
import com.example.javafxmysqlproject.model.dao.GenericDao;
import com.example.javafxmysqlproject.model.entities.Department;
import com.example.javafxmysqlproject.model.entities.Seller;

import java.util.List;

public class SellerService {

    private GenericDao dao = DaoFactory.createSellerDao();

    public List<Seller> findAll() {
        return dao.findAll();
    }

    public void saveOrUpdate(Seller seller) {
        if (seller.getId() == null) {
            dao.insert(seller);
        } else {
            dao.update(seller);
        }
    }

    public void remove(Seller seller) {
        dao.deleteById(seller.getId());
    }

}
