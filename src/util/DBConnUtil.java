package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class DBConnUtil {

    public static Connection getConnection(String propertyFilePath) {
        Connection conn = null;
        try {
            Properties props = new Properties();

            // Correct way: Load properties using ClassLoader
            InputStream inputStream = DBConnUtil.class.getClassLoader().getResourceAsStream(propertyFilePath);
            if (inputStream == null) {
                System.out.println("Cannot find properties file at path: " + propertyFilePath);
                return null;
            }
            props.load(inputStream);

            String url = props.getProperty("db.url");
            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");

            conn = DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {
            System.out.println("SQL Exception during connection: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO Exception reading properties file: " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }
}
