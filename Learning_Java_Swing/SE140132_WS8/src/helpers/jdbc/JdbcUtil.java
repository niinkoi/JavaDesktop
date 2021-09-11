/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers.jdbc;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Khoi Nguyen
 */
public class JdbcUtil {
    
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    static {
        DBConfig dbConfig = new DBConfig();
        driver = dbConfig.getDriver();
        url = dbConfig.getUrl();
        username = dbConfig.getUsername();
        password = dbConfig.getPassword();

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Get the connection from the instance
     * @return {@link Connection}
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * Release the connection to database
     * @param con connection
     * @throws SQLException
     */
    private static void releaseConnection(Connection con) throws SQLException {
        if(con != null) {
            con.close();
        }
    }

    /**
     * Release statement connection
     * @param statement
     * @throws SQLException
     */
    private static void releaseStatement(Statement statement) throws SQLException {
        if(statement != null) {
            statement.close();
        }
    }

    /**
     * Release result set
     * @param rs
     * @throws SQLException
     */
    private static void releaseResultSet(ResultSet rs) throws SQLException {
        if(rs != null) {
            rs.close();
        }
    }

    public static void release(Connection con, Statement statement, ResultSet rs) throws SQLException {
        releaseConnection(con);
        releaseStatement(statement);
        releaseResultSet(rs);
    }

}
