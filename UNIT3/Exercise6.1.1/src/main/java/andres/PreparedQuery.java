package andres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PreparedQuery {
    LogConfig logger = new LogConfig("LogFile1.txt");

    public void launch() {
        String URL = "jdbc:postgresql://localhost:5432/VTInstitute";
        String user = "postgres";
        String password = "postgres";

        boolean looping = true;
        try (Connection connection = DriverManager.getConnection(URL, user, password)) {
            while (looping) {
                looping = userInteractions(connection);
            }
        } catch (SQLException e) {
            logger.addLogWarning("Existe un error al conectar a la base de datos. " + e.getMessage());
        }
    }

    private boolean userInteractions(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        String SQLPrepared = "INSERT INTO subjects (name, year) VALUES (?,?)";
        PreparedStatement prepared = connection.prepareStatement(SQLPrepared);

        System.out.println("Please insert the data of the new subject:");
        System.out.println("Insert the 'NAME' of the subject");
        String name = scanner.nextLine();
        System.out.println("Insert the 'YEAR' of the subject");
        int year = scanner.nextInt();

        prepared.setString(1, name);
        prepared.setInt(2, year);

        System.out.println("Do you want to add another subject?\ny/n");
        String repeatLoop = scanner.nextLine();
        if (repeatLoop.equals("y"))
            return true;
        return false;
    }
}
