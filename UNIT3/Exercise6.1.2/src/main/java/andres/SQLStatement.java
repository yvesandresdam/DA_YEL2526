package andres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLStatement {
    public void start(){
        String URL = "jdbc:postgresql://localhost:5432/VTInstitute";
        String user = "postgres";
        String password = "postgres";

        // CREATE Table sentences
        try (Connection connection = DriverManager.getConnection(URL, user, password)){
            // SQL Statement
            String SQLStatement = "CREATE TABLE courses (code SERIAL PRIMARY KEY, name VARCHAR (90) NOT NULL)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQLStatement);
            // CREATE execute
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // INSERT Values sentences
        try (Connection connection = DriverManager.getConnection(URL, user, password)){
            // SQL Statement
            String SQLStatement = "INSERT INTO courses (name) VALUES (?)";
            // Prepared statement instatiation
            PreparedStatement preparedStatement = connection.prepareStatement(SQLStatement);
            // Insert values
            preparedStatement.setString(1,"Multiplatform app development");
            preparedStatement.executeUpdate();

            preparedStatement.setString(1,"Web development");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
