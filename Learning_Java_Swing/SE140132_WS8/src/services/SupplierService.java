/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.SupplierDao;
import dto.Suppliers;
import helpers.tables.SupplierTableModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class SupplierService implements IService<Suppliers>{

    private SupplierDao supplierDao = new SupplierDao();
    
    @Override
    public void setFieldsEmpty(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }

    @Override
    public void loadDataToTable(JTable table) {
        try {
            TableModel tableModel = new SupplierTableModel((List<Suppliers>) supplierDao.getList());
            table.setModel(tableModel);
            table.updateUI();
        } catch (SQLException ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
   }

    @Override
    public void saveAction(JFrame frame, Suppliers supplier) throws SQLException {
        boolean isExisted = !getList().stream().anyMatch(i -> i.getCode().equals(supplier.getCode()));
        if (!isExisted) {
            JOptionPane.showMessageDialog(frame, supplier.getCode() + " has existed in database");
        } else {
            String result = supplierDao.save(supplier);
            JOptionPane.showMessageDialog(frame, "Save " + supplier.getName() + " " + result);
        }
    }

    @Override
    public void deleteAction(String theId, JFrame frame) {
        
        boolean isPersisted = new ItemService().getList()
                .stream()
                .anyMatch(i -> i.getSupplier().getCode().equals(theId));
        
        try {
            if (isPersisted) {
                JOptionPane.showMessageDialog(frame, "This " + theId + " still has registed items.\n Can not delete");
            } else {
                String result = supplierDao.delete(theId);
                JOptionPane.showMessageDialog(frame, "Delete " + theId + " " + result);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Suppliers getClickedRow(JTable table) {
        int selectedRow = table.getSelectedRow();
        String code = getValueAtRow(table, selectedRow, 0);
        
        return findByCode(code).orElse(null);
    }
    
    private String getValueAtRow(JTable table, int row, int col) {
        return (String) table.getValueAt(row, col);
    }

    @Override
    public List<Suppliers> getList() {
        List<Suppliers> list = new ArrayList<>();
        try {
            list = (List<Suppliers>) supplierDao.getList();
        } catch (SQLException ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public Optional<Suppliers> findByCode(String code) {
        return getList()
                .stream()
                .filter(supplier -> supplier.getCode().equals(code))
                .findAny();
    }

    @Override
    public void updateAction(JFrame frame, Suppliers supplier) throws SQLException {
        String result = supplierDao.update(supplier, 3);
        JOptionPane.showMessageDialog(frame, "Update " + supplier.getName() + " " + result);
    }
    
    
    public Suppliers getSupplierFromComboBox(JComboBox comboBox) {
        String info = (String) comboBox.getSelectedItem();
        return findByCode((info.split("-"))[0]).get();
    }
    
}
