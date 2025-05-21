package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static final String URL = "jdbc:mysql://localhost:3306/pdf_converter";
    private static final String USER = "root";
    private static final String PASS = "";

    static {
        try {
            // ép JVM load driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ JDBC Driver đã được load.");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Không tìm thấy MySQL JDBC Driver");
        }
    }

    public static Connection getConnection() throws SQLException {
        System.out.println("🔄 Đang kết nối đến CSDL...");
        Connection conn = DriverManager.getConnection(URL, USER, PASS);
        System.out.println("✅ Đã kết nối CSDL thành công!");
        return conn;
    }
}
