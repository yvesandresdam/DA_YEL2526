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

        try(Connection connection = DriverManager.getConnection(URL,user,password)){
            String SQLStatement = "ALTER TABLE subjects ADD COLUMN course INTEGER";
            PreparedStatement preparedStatement = connection.prepareStatement(SQLStatement);
            preparedStatement.executeUpdate();

            SQLStatement = "ALTER TABLE subjects ADD CONSTRAINT fk_course FOREIGN KEY (course) REFERENCES courses(code)";
            preparedStatement = connection.prepareStatement(SQLStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
