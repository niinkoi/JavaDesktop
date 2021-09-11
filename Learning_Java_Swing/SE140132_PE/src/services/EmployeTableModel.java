/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dto.Employee;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Khoi Nguyen
 */
public class EmployeTableModel extends AbstractTableModel {
    
    private List<Employee> list;
    private String[] headers;

    public EmployeTableModel(List<Employee> list) {
        super();
        this.list = list;
        headers = new String[] {"Code", "Name", "Address", "Salary"};
    }
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Employee emp = list.get(row);
        switch(col) {
            case 0: return emp.getCode();
            case 1: return emp.getName();
            case 2: return emp.getAddress();
            case 3: return emp.getSalary();
        }
        return null;
    }
    
    @Override
    public String getColumnName(int col) {
        return headers[col];
    }
}
