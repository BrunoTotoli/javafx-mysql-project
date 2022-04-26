package com.example.javafxmysqlproject.model.dao.imp;

import com.example.javafxmysqlproject.db.DB;
import com.example.javafxmysqlproject.db.DBException;
import com.example.javafxmysqlproject.model.dao.GenericDao;
import com.example.javafxmysqlproject.model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoImp implements GenericDao<Department> {

    private Connection conn = null;

    public DepartmentDaoImp(Connection connection) {
        this.conn = connection;
    }

    @Override
    public void insert(Department department) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.prepareStatement("insert into department(name)" +
                    "values (?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, department.getName());
            int rowsAffected = statement.executeUpdate();
            rs = statement.getGeneratedKeys();
            if (rowsAffected > 0) {
                if (rs.next()) {
                    department.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement("delete from department where Id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
        }

    }

    @Override
    public void update(Department department) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement("update department set Name = ? where Id = ?");
            statement.setString(1, department.getName());
            statement.setInt(2, department.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.prepareStatement("select * from department where id = ? ");
            statement.setInt(1, id);
            rs = statement.executeQuery();

            if (rs.next()) {
                Department dep = new Department(rs.getInt(1), rs.getString(2));
                return dep;
            }
            return null;

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(statement);
        }


    }

    @Override
    public List<Department> findAll() {
        List<Department> list = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            statement = conn.prepareStatement("select * from department");
            rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new Department(rs.getInt("Id"), rs.getString("Name")));
            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
            DB.closeResultSet(rs);
        }

        return list;
    }
}
