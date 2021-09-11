/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * JDBC Helper class
 * @author Khoi Nguyen
 */
public class JdbcHelper {
    
    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;

    public static List<Map<String, Object>> queryList(String sql) throws SQLException {
        ResultSet resultSet = null;

        try {
            getPreparedStatement(sql);
            resultSet = preparedStatement.executeQuery();
            return resultToListMap(resultSet);
        } finally {
            release(resultSet);
        }
    }

    private static List<Map<String, Object>> resultToListMap(ResultSet resultSet) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();
        while(resultSet.next()) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            Map<String, Object> map = new HashMap<>();
            for(int i = 1; i < metaData.getColumnCount() + 1; i++) {
                map.put(metaData.getColumnLabel(i), resultSet.getObject(i));
            }
            list.add(map);
        }
        return list;
    }

    @SuppressWarnings("rawtypes")
	public static List queryList(String sql, Object... params) throws SQLException {
        ResultSet resultSet = null;
        try {
            getPreparedStatement(sql);
            for(int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            resultSet = preparedStatement.executeQuery();
            return resultToListMap(resultSet);
        } finally {
            release(resultSet);
        }
    }

    public static Optional<Object> querySingle(String sql) throws SQLException {
        Optional<Object> result = null;
        ResultSet resultSet = null;

        try {
            getPreparedStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                result = Optional.ofNullable(resultSet.getObject(1));
            }
            return result;
        } finally {
            release(resultSet);
        }
    }

    public static Object querySingle(String sql, Object... params) throws SQLException {
        Object result = null;
        ResultSet resultSet = null;
        try {
            getPreparedStatement(sql);
            for(int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                result = resultSet.getObject(1);
            }
            return result;
        } finally {
            release(resultSet);
        }
    }

    public static int update(String sql) throws SQLException {
        try {
            getPreparedStatement(sql);
            return preparedStatement.executeUpdate();
        } finally {
            release();
        }
    }

    public static int update(String sql, Object... params) throws SQLException {
        try {
            getPreparedStatement(sql);
            for(int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement.executeUpdate();
        } finally {
            release();
        }
    }

    private static void getPreparedStatement(String sql) throws SQLException {
        connection = JdbcUtil.getConnection();
        preparedStatement = connection.prepareStatement(sql);
    }

    private static void release() throws SQLException {
        release(null);
    }

    private static void release(ResultSet resultSet) throws SQLException {
        release(connection, preparedStatement, resultSet);
    }

    private static void release(ResultSet resultSet, Statement statement) throws SQLException {
        release(connection, statement, resultSet);
    }

    private static void release(Connection connection, Statement statement,
                                ResultSet resultSet) throws SQLException {
        JdbcUtil.release(connection, statement, resultSet);
    }
}
