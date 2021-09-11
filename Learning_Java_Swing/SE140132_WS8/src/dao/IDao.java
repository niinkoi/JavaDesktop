package dao;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Interface for implementing Data Access Object
 * @param <T> Injecting object
 * @author Khoi Nguyen
 */
public interface IDao<T> {
    
     /**
     * Saving objects to database then notify the message
     * @param t object to save
     * @return message to notify that process works or not
     * @throws SQLException 
     */
    String save(T t) throws SQLException;

    /**
     * Updating existed objects in database then notify the message
     * @param t object to update
     * @param amount fields to update
     * @return message to notify that process works or not
     */
    String update(T t, int amount) throws SQLException;

    /**
     * Deleting existed objects in database by id
     * @param theId
     * @return message to notify that process works or not
     * @throws SQLException
     */
    String delete(String theId) throws SQLException;

    /**
     * Get object from database based on id
     * @param theId
     * @return found object
     * @throws SQLException 
     */
    Object findById(String theId) throws SQLException;

    /**
     * Get all objects from database
     * @return collection of objects
     * @throws SQLException
     */
    Collection<T> getList() throws SQLException;
}
