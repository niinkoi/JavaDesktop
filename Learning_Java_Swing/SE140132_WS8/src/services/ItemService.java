/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.ItemDao;
import dto.Items;
import dto.Suppliers;
import helpers.tables.ItemTableModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

/**
 *
 * @author Khoi Nguyen
 */
public class ItemService implements IService<Items>{
    
    private ItemDao itemDao = new ItemDao();
    
    @Override
    public void setFieldsEmpty(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }

    @Override
    public void loadDataToTable(JTable table) {
        try {
            TableModel tableModel = new ItemTableModel((List<Items>) itemDao.getList());
            table.setModel(tableModel);
            table.updateUI();
        } catch (SQLException ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
   }

    @Override
    public void saveAction(JFrame frame, Items item) throws SQLException {
        boolean isExisted = !getList().stream().anyMatch(i -> i.getCode().equals(item.getCode()));
        if (!isExisted) {
            JOptionPane.showMessageDialog(frame, item.getCode() + " has existed in database");
        } else {
            String result = itemDao.save(item);
            JOptionPane.showMessageDialog(frame, "Save " + item.getName() + " " + result);
        }
    }
    
    public void loadDataToComboBox(List<Suppliers> suppliers, JComboBox comboBox) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        suppliers.forEach(supplier -> model.addElement(supplier.toString()));
        comboBox.setModel(model);
    }

    @Override
    public void deleteAction(String theId, JFrame frame) {
        try {
            String result = itemDao.delete(theId);
            JOptionPane.showMessageDialog(frame, "Delete " + theId + result);
        } catch (SQLException ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Items getClickedRow(JTable table) {
        int selectedRow = table.getSelectedRow();
        String code = getValueAtRow(table, selectedRow, 0);
        
        return findByCode(code).orElse(null);
    }
    
    private String getValueAtRow(JTable table, int row, int col) {
        return (String) table.getValueAt(row, col);
    }

    @Override
    public List<Items> getList() {
        List<Items> list = new ArrayList<>();
        try {
            list = (List<Items>) itemDao.getList();
        } catch (SQLException ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    private Optional<Items> findByCode(String code) {
        return getList()
                .stream()
                .filter(item -> item.getCode().equals(code))
                .findAny();
    }

    
    @Override
    public void updateAction(JFrame frame, Items item) throws SQLException {
        String result = itemDao.update(item, 5);
        JOptionPane.showMessageDialog(frame, "Update " + item.getName() + " " + result);
    }
    
}
