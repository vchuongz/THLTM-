package dao;

import model.Job;
import java.sql.*;

public class JobDAO {
    public static void save(Job job) {
        String sql = "INSERT INTO jobs(filename, output_path, status) VALUES(?,?,?)";
        try (Connection conn = DB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, job.getFilename());
            ps.setString(2, job.getOutputPath());
            ps.setString(3, "DONE");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}