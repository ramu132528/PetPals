package main;

import util.DBConnUtil;
import java.sql.Connection;

public class DBConnectionTest {
    public static void main(String[] args) {
        try {
            Connection conn = DBConnUtil.getConnection("db.properties");
            if (conn != null) {
                System.out.println("Database connection successful!");
            } else {
                System.out.println("Database connection failed!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
