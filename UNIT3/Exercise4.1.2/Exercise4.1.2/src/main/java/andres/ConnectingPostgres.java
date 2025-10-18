package andres;

import java.sql.*;

public class ConnectingPostgres {
    public void launch() {
        // Database connection Strings
        String url = "jdbc:postgresql://localhost:5432/VTInstitute";
        String user = "postgres";
        String password = "postgres";

        // Object Connection
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, user, password);

            // SQL updates
            Statement query = connection.createStatement();
            String SQLQuery = "INSERT INTO subjects (name,year) VALUES ('Markup Languages',1)";
            int rowsAffected = query.executeUpdate(SQLQuery);

            // displaying results
            System.out.println("Number of affected rows: " + rowsAffected);

            // closing connection
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
