package andres;

import java.sql.*;

public class ConnectingPostgres {
    public void launch() throws SQLException {
        // Database information with the connection Strings
        String url = "jdbc:postgresql://localhost:5432/VTInstitute";
        String user = "postgres";
        String password = "postgres";

        // Object connection
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, user, password);

            // SQL Queries
            Statement query = connection.createStatement();
            String SQLQuery = "SELECT * FROM subjects";
            ResultSet result = query.executeQuery(SQLQuery);

            System.out.println("Number\t\tSubject\t\tYear");
            System.out.println("-----------------------------------------");

            while (result.next()) {
                String count = result.getString(1);
                String subject = result.getString(2);
                String year = result.getString(3);

                displayRows(count, subject, year);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null)
                connection.close();
        }
    }

    private void displayRows(String count, String subject, String year){
        // This function displays the rows from the PostgreSQL Table
        // First: calculate the number of tabulators "\t"
        int tabulatorsNumber = UIConsole.calculateStringLongitude(subject);
        String tabulatorsCharacters = "";
        for(int i = 0; i < tabulatorsNumber; i++){
            tabulatorsCharacters += "\t";
        }
        // Second: displays the row information
        System.out.println(count + "\t\t" + subject + tabulatorsCharacters + year);
    }
}
