package dao;

import dto.Items;
import dto.Suppliers;
import helpers.jdbc.JdbcHelper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.SupplierService;
import utils.Constants;

/**
 *
 * @author Khoi Nguyen
 */
public class ItemDao implements IDao<Items> {
    
    private static final int NUMBER_OF_FIELDS = 6;
    private static final String OBJECT = "Items";
    private static final String CODE_NAME = "itemCode";

    @Override
    public String save(Items item) throws SQLException {
        int executeUpdate = JdbcHelper.update(Constants.Query.saveQuery(OBJECT, NUMBER_OF_FIELDS),
                item.getCode(), item.getName(), item.getSupplier().getCode(),
                item.getUnit(), item.getPrice(), item.isSupplying());
        
        return (executeUpdate == 1) ? Constants.Message.SUCCESSFULLY : Constants.Message.FAILED;
    }

    @Override
    public String update(Items item, int amount) throws SQLException {
        int executeUpdate = JdbcHelper.update(Constants.Query.updateQuery(OBJECT, CODE_NAME, amount), 
                item.getName(), item.getSupplier().getCode(),
                item.getUnit(), item.getPrice(), item.isSupplying(),
                item.getCode());
        
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
    public Collection<Items> getList() throws SQLException {
        List<Items> list = new ArrayList<>();
        List<Map<String, Object>> rows = new ArrayList<>();
        
        rows = JdbcHelper.queryList(Constants.Query.getListQuery(OBJECT));
        if (rows.isEmpty()) {
            return null;
        }
        
        for (Map<String, Object> row: rows) {
            Items item = new Items();
            row.entrySet().forEach((entry) -> {
                switch(entry.getKey()) {
                    case "itemCode":
                        item.setCode((String) entry.getValue());
                        
                        break;
                    case "itemName":
                        item.setName((String) entry.getValue());
                        break;
                    case "supCode":
                        {
                            item.setSupplier((Suppliers) new SupplierService()
                                    .findByCode((String) entry.getValue()).orElse(null));
                        }
                        break;
                    case "unit":
                        item.setUnit((String) entry.getValue());
                        break;
                    case "price":
                        item.setPrice(Float.parseFloat(String.valueOf(entry.getValue())));
                        break;
                    case "supplying":
                        item.setSupplying((Boolean) entry.getValue());
                        break;
                }
            });
            list.add(item);
        }
        return list;
    }
    
}
