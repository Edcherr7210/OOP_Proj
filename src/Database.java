import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    // This is your SQLite file
    private static final String URL = "jdbc:sqlite:PrioritiCal.db";

    // Get a connection to the DB file
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    // Create tables if they don't exist
    public static void init() {
        String sql = """
            CREATE TABLE IF NOT EXISTS students (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                first_name TEXT NOT NULL,
                last_name  TEXT NOT NULL,
                email      TEXT NOT NULL UNIQUE,
                password   TEXT NOT NULL,
                major      TEXT
            );
            """;

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("DB ready.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
