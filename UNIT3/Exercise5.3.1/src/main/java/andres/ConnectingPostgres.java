package andres;

import java.sql.*;

public class ConnectingPostgres {
    public void launch(){
        String url = "jdbc:postgresql://localhost:5432/VTInstitute";
        String user = "postgres";
        String password = "postgres";

        // The try-with-resources is declared at the try-catch head block
        try(Connection connection = DriverManager.getConnection(url, user, password)) {
            Statement query = connection.createStatement();
            String SQLQuery = "SELECT * FROM subjects";
            ResultSet result = query.executeQuery(SQLQuery);

            System.out.println("Number\t\tSubject\t\t\tYear");
            System.out.println("-----------------------------------------");

            while(result.next()){
                String count = result.getString(1);
                String subject = result.getString(2);
                String year = result.getString(3);
                System.out.println(count + "\t" + subject + "\t\t" + year);
            }
            // These lines are innecesary with a 'try-with-resources'
            // result.close();
            // connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
