package com.example.javafxmysqlproject.model.services;

import com.example.javafxmysqlproject.model.entities.Department;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DepartmentService {

    public List<Department> findAll() {
        // MOCK (Dados TEST)
        return Arrays.asList(new Department(1, "Books"),
                new Department(2, "Computers"),
                new Department(3, "Phones"),
                new Department(4, "Foods"));
    }

}
