/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Khoi Nguyen
 */
public interface IService<T> {
    
    /**
     * Set all fields empty
     * @param fields 
     */
    void setFieldsEmpty(JTextField... fields);
    
    /**
     * Load list results into table
     * @param table 
     */
    void loadDataToTable(JTable table);
    
    /**
     * Process save action
     * @param frame
     * @param t
     * @throws SQLException 
     */
    void saveAction(JFrame frame, T t) throws SQLException;
    
    /**
     * Process update action
     * @param frame
     * @param t
     * @throws SQLException 
     */
    void updateAction(JFrame frame, T t) throws SQLException;
    
    /**
     * Process delete action
     * @param code
     * @param frame 
     */
    void deleteAction(String code, JFrame frame);
    
    /**
     * Get object by clicking a specific row
     * @param table
     * @return T
     */
    T getClickedRow(JTable table);
    
    /**
     * Get list
     * @return list 
     */
    List<T> getList();
    
}
