/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Employee;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import jdbc.JdbcHelper;

/**
 *
 * @author Khoi Nguyen
 */
public class EmployeeDao {
    
    private final String SAVE_QUERY = "INSERT INTO tbl_Employee VALUES (?, ?, ?, ?)";
    private final String DELTE_QUERY = "DELETE FROM tbl_Employee WHERE Code = ?";
    private final String GET_ALL_QUERY = "SELECT * FROM tbl_Employee";
    
    private final String MSG_SUCCESS = "successfully";
    private final String MSG_FAIL = "failed";
    
    public String save(Employee emp) throws SQLException {
        int executeUpdate = JdbcHelper.update(SAVE_QUERY,
                emp.getCode(), emp.getName(), emp.getAddress(), emp.getSalary());
        
        return (executeUpdate == 1) ? MSG_SUCCESS : MSG_FAIL ;
    }

    
    public String delete(String code) throws SQLException {
        int deletedCount = JdbcHelper.update(DELTE_QUERY, code);
        return (deletedCount == 1) ? MSG_SUCCESS : MSG_FAIL;
    }
    
    public List<Employee> getList() throws SQLException {
        List<Employee> list = new ArrayList<>();
        List<Map<String, Object>> rows = new ArrayList<>();
        
        rows = JdbcHelper.queryList(GET_ALL_QUERY);
        if (rows.isEmpty()) {
            return null;
        }
        
        for (Map<String, Object> row: rows) {
            Employee emp = new Employee();
            row.entrySet().forEach((entry) -> {
                switch(entry.getKey()) {
                    case "Code":
                        emp.setCode((String) entry.getValue());
                        break;
                    case "Name":
                        emp.setName((String) entry.getValue());
                        break;
                    case "Address":
                        emp.setAddress((String) entry.getValue());
                        break;
                    case "Salary":
                        emp.setSalary(Integer.parseInt(String.valueOf(entry.getValue())));
                        break;
                }
            });
            list.add(emp);
        }
        return list;
    }
    
}
