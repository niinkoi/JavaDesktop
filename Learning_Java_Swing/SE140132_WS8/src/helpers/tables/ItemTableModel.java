/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers.tables;

import dto.Items;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Khoi Nguyen
 */
public class ItemTableModel extends AbstractTableModel {

    private List<Items> list;
    private String[] headers;

    public ItemTableModel(List<Items> list) {
        super();
        this.list = list;
        headers = new String[] {"itemCode", "itemName", "supCode",
                                "unit", "price", "supplying"};
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
        Items item = list.get(row);
        switch(col) {
            case 0: return item.getCode();
            case 1: return item.getName();
            case 2: return item.getSupplier().getCode();
            case 3: return item.getUnit();
            case 4: return item.getPrice();
            case 5: return item.isSupplying();
        }
        return null;
    }
    
    @Override
    public String getColumnName(int col) {
        return headers[col];
    }
    
}
