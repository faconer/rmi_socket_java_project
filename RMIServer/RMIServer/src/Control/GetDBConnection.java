/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author The
 */
public class GetDBConnection {
    private Connection con;
    private String url  = "jdbc:sqlserver://THEMY:1433;databaseName=QuickMath";
    private String user  = "sa";
    private String password = "123456";
    public GetDBConnection() {
        con = null;
        getDBConnection(url, user, password);
    }

    public Connection getConnection() {
        
        return con;
    }
    public void getDBConnection(String url, String user, String password){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url, user, password);
        } 
        catch (ClassNotFoundException e) {
            System.out.println("Class was not found.");
        }
        catch(SQLException e){
            System.out.println("Error connection!");
        }
    }
}
