/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {

    private static DBContext _instance = null;
    private Connection connection = null;

    public static DBContext getInstance() {
        if (_instance == null) {
            _instance = new DBContext();
        }

        return _instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public DBContext(String URL, String userName, String password) {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connection = DriverManager.getConnection(URL, userName, password);
            }
        } catch (Exception e) {
            connection = null;
        }
    }

    public DBContext() {
        this("jdbc:sqlserver://DEVHOA;databaseName=PRJ301Project;TrustServerCertificate=true;", "sa", "sa");
        // this("jdbc:sqlserver://localhost:1433;databaseName=PRJRainLoz;TrustServerCertificate=true;",
        // "sa","sa");
        // this("jdbc:sqlserver://localhost:1433;databaseName=PRJ301Project;TrustServerCertificate=true;",
        // "sa","123");
    }
}
