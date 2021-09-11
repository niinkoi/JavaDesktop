/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Database configuration
 * @author Khoi Nguyen
 */
public class DBConfig {
    
    private String driver;
    private String url;
    private String username;
    private String password;

    public DBConfig() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("database.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            this.driver = properties.getProperty("db.driver");
            this.url = properties.getProperty("db.url");
            this.username = properties.getProperty("db.username");
            this.password = properties.getProperty("db.password");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "DBConfig{" + "driver=" + driver + ", url=" + url + ", username=" + username + ", password=" + password + '}';
    }

}
