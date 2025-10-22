package andres;

import java.sql.*;

public class ConnectingPostgres3 {
    public void launch(){
        String url = "jdbc:postgresql://localhost:5432/VTInstitute";
        String user = "postgres";
        String password = "postgres";

        // The try-with-resources is declared at the try-catch head block
        try(Connection connection = DriverManager.getConnection(url, user, password)) {
            Statement query = connection.createStatement();
            String SQLQuery = "ALTER TABLE subjects ADD Hour INTEGER";
            int rowsAffected = query.executeUpdate(SQLQuery);

            System.out.println(rowsAffected);

            // This line is unnecesary with a 'try-with-resources'
            // connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
