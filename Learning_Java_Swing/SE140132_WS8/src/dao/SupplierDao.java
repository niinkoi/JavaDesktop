package dao;

import dto.Suppliers;
import helpers.jdbc.JdbcHelper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import utils.Constants;

/**
 *
 * @author Khoi Nguyen
 */
public class SupplierDao implements IDao<Suppliers> {

    private static final int NUMBER_OF_FIELDS = 4;
    private static final String OBJECT = "Supliers";
    private static final String CODE_NAME = "supCode";
    
    @Override
    public String save(Suppliers supplier) throws SQLException {
        int executeUpdate = JdbcHelper.update(Constants.Query.saveQuery(OBJECT, NUMBER_OF_FIELDS),
                supplier.getCode(), supplier.getName(), 
                supplier.getAddress(), supplier.isColloborating());
        
        return (executeUpdate == 1) ? Constants.Message.SUCCESSFULLY : Constants.Message.FAILED;
    }

    @Override
    public String update(Suppliers supplier, int amount) throws SQLException {
         int executeUpdate = JdbcHelper.update(Constants.Query.updateQuery(OBJECT, CODE_NAME, amount), 
                supplier.getName(), supplier.getAddress(), supplier.isColloborating(), supplier.getCode());
        
        return (executeUpdate == 1) ? Constants.Message.SUCCESSFULLY : Constants.Message.FAILED;
    }

    @Override
    public String delete(String theId) throws SQLException {
        int deletedCount = JdbcHelper.update(Constants.Query.deleteQuery(OBJECT, CODE_NAME), theId);
        return (deletedCount == 1) ? Constants.Message.SUCCESSFULLY
                                : Constants.Message.FAILED;
    }

    @Override
    public Object findById(String theId) throws SQLException {
        return null;
    }

    @Override
    public Collection<Suppliers> getList() throws SQLException {
        List<Suppliers> list = new ArrayList<>();
        List<Map<String, Object>> rows = new ArrayList<>();
        
        rows = JdbcHelper.queryList(Constants.Query.getListQuery(OBJECT));
        if (rows.isEmpty()) {
            return null;
        }
        
        for (Map<String, Object> row: rows) {
            Suppliers supplier = new Suppliers();
            row.entrySet().forEach((entry) -> {
                switch(entry.getKey()) {
                    case "supCode":
                        supplier.setCode((String) entry.getValue());
                        break;
                    case "supName":
                        supplier.setName((String) entry.getValue());
                        break;
                    case "address":
                        supplier.setAddress((String) entry.getValue());
                        break;
                    case "colloborating":
                        supplier.setColloborating((Boolean) entry.getValue());
                        break;
                }
            });
            list.add(supplier);
        }
        return list;
    }
    
}
