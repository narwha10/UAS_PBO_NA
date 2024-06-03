package notesapp1;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class koneksi {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL1 = "jdbc:mysql://127.0.0.1:3306/notes";
    private static final String URL2 = "jdbc:mysql://localhost:3307/notes";
    private static final String USER = "root"; // Replace with your MySQL username
    private static final String PASSWORD = ""; // Replace with your MySQL password

    public static Connection getKoneksi() {
        Connection koneksi = null;
        try {
            Class.forName(JDBC_DRIVER);

            // Determine the database URL based on the hostname
            String hostName = InetAddress.getLocalHost().getHostName();
            String url;

            if ("device1".equals(hostName)) {
                url = URL1;
            } else if ("device2".equals(hostName)) {
                url = URL2;
            } else {
                throw new RuntimeException("Unknown host: " + hostName);
            }

            koneksi = DriverManager.getConnection(url, USER, PASSWORD);
            System.out.println("Berhasil Koneksi ke Database dengan URL: " + url);
        } catch (SQLException e) {
            System.out.println("Error Koneksi: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Driver class not found: " + e.getMessage());
        } catch (UnknownHostException e) {
            System.out.println("Error getting hostname: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Runtime error: " + e.getMessage());
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
