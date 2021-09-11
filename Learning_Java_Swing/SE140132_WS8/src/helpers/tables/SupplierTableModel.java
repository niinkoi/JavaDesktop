/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers.tables;

import dto.Suppliers;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Khoi Nguyen
 */
public class SupplierTableModel extends AbstractTableModel {

    private List<Suppliers> list;
    private String[] headers;

    public SupplierTableModel(List<Suppliers> list) {
        this.list = list;
        this.headers = new String[] {"supCode", "supName", "address", "cobolorating"};;
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
        Suppliers supplier = list.get(row);
        switch(col) {
            case 0: return supplier.getCode();
            case 1: return supplier.getName();
            case 2: return supplier.getAddress();
            case 3: return supplier.isColloborating();
        }
        return null;
    }
    
    @Override
    public String getColumnName(int col) {
        return headers[col];
    }
    
}
