package moe.nova.playground;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DuckDBTest {
    static void main() throws SQLException {
        var conn = DriverManager.getConnection("jdbc:duckdb:");
        // insert
        var stmt = conn.createStatement();
        stmt.executeUpdate("CREATE TABLE test (id INTEGER PRIMARY KEY, name VARCHAR(100))");
        stmt.executeUpdate("INSERT INTO test (id, name) VALUES (1, 'Alice'), (2, 'Bob')");

        // query
        var rs = stmt.executeQuery("SELECT * FROM test");
        while (rs.next()) {
            IO.println(rs.getInt(1) + " " + rs.getString(2));
        }

    }
}
