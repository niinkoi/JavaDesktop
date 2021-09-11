/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.EmployeeDao;
import dto.Employee;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author Khoi Nguyen
 */
public class EmployeeService {
    
    private EmployeeDao empDao = new EmployeeDao();

    public void loadDataToTable(JTable table) {
        try {
            TableModel tableModel = new EmployeTableModel((List<Employee>) empDao.getList());
            table.setModel(tableModel);
            table.updateUI();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeService.class.getName()).log(Level.SEVERE, null, ex);
        }
   }

    public void loadDataToTable(List<Employee> list, JTable table) {
        TableModel tableModel = new EmployeTableModel(list);
        table.setModel(tableModel);
        table.updateUI();
    }
    
    public void saveAction(JFrame frame, Employee emp) throws SQLException {
        boolean isExisted = !getList().stream().anyMatch(i -> i.getCode().equals(emp.getCode()));
        if (!isExisted) {
            JOptionPane.showMessageDialog(frame, emp.getCode() + " has existed in database");
        } else {
            String result = empDao.save(emp);
            JOptionPane.showMessageDialog(frame, "Save " + emp.getName() + " " + result);
        }
    }
    
    public void deleteAction(String code, JFrame frame) {
        try {
            String result = empDao.delete(code);
            JOptionPane.showMessageDialog(frame, "Delete " + code + result);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public Employee getClickedRow(JTable table) {
        int selectedRow = table.getSelectedRow();
        String code = getValueAtRow(table, selectedRow, 0);
        
        return findByCode(code).orElse(null);
    }
    
    private String getValueAtRow(JTable table, int row, int col) {
        return (String) table.getValueAt(row, col);
    }

    
    public List<Employee> getList() {
        List<Employee> list = new ArrayList<>();
        try {
            list = (List<Employee>) empDao.getList();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    private Optional<Employee> findByCode(String code) {
        return getList()
                .stream()
                .filter(emp -> emp.getCode().equals(code))
                .findAny();
    }
    
    public List<Employee> findBySalaryRange(List<Employee> list, int from, int to) {
        List<Employee> foundList = new ArrayList<>();
        list.stream()
                .filter(e -> e.getSalary() <= to && e.getSalary() >= from)
                .forEach(foundList::add);
        return foundList;
    }
    
}
