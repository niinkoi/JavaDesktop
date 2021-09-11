/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

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
        this.driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        this.url = "jdbc:sqlserver://DESKTOP-JVVEMUA\\\\\\\\SQLEXPRESS:1433;database=SE140132_DB";
        this.username = "sa";
        this.password = "123456";
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
