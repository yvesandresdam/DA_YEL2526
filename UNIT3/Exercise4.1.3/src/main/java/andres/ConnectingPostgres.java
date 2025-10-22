package andres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectingPostgres {
    public void launch(){
        // Database connections Strings
        String url = "jdbc:postgresql://localhost:5432/VTInstitute";
        String user = "postgres";
        String password = "postgres";

        try {
            // Object connection
            Connection connection = DriverManager.getConnection(url, user, password);

            // SQL DML
            Statement query = connection.createStatement();
            String SQLQuery = "ALTER TABLE subjects ADD Hour INTEGER";

            // Display information
            int rowsAffected = query.executeUpdate(SQLQuery);
            System.out.println(rowsAffected);

            // Closing connection
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
