package notesapp1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class koneksi {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/notes";
    private static final String USER = "root"; // Replace with your MySQL username
    private static final String PASSWORD = ""; // Replace with your MySQL password

     // untuk mendapatkan koneksi ke database
    public static Connection getKoneksi() {
        Connection koneksi = null;
        try {
            Class.forName(JDBC_DRIVER);
            koneksi = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Berhasil Koneksi ke Database");
        } catch (SQLException e) {
            System.out.println("Error Koneksi: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Driver class not found: " + e.getMessage());
        }
        return koneksi;
    }

    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Error saat menutup koneksi: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = getKoneksi();
        } catch (Exception e) {
            System.out.println("Error Utama: " + e.getMessage());
        } finally {
            close(conn);
        }
    }
}
