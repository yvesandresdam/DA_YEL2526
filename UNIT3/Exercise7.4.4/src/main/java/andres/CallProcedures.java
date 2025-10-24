package andres;

import java.sql.*;

public class CallProcedures {
    public void start() {
        String URL = "jdbc:postgresql://localhost:5432/employees";
        String user = "postgres";
        String password = "postgres";

        Connection connection;

        try {
            connection = DriverManager.getConnection(URL, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //testConnection(connection);

        // Functions calling
        System.out.println("First function call:");
        String SQLFirstFunction = DBFunctions.SELECT_EMPLOYEES_WITH_SALESMAN;
        callFunction(connection, SQLFirstFunction);

        System.out.println("Second function call:");
        String[] job = {"SALESMAN", "ANALYST", "CLERK", "MANAGER", "PRESIDENT"};
        String SQLSecondFunction = String.format("{call listofemployeeswithjob('%s')}", job[4]);
        callFunction(connection, SQLSecondFunction);

        System.out.println("Third function call:");
        String partialName = "AM";
        String SQLThirdFunction = String.format("{call listofemployeeswithname('%s')}", partialName);
        callFunction(connection, SQLThirdFunction);
    }

    private void testConnection(Connection connection) {
        if (connection != null)
            System.out.println("The application is connected to the database");
    }

    private void callFunction(Connection connection, String SQLFunction) {
        CallableStatement callable;
        ResultSet result;
        try {
            callable = connection.prepareCall(SQLFunction);
            result = callable.executeQuery();
            while (result.next()) {
                String output1 = result.getString(1);
                String output2 = result.getString(2);
                String output3 = result.getString(3);
                String output4 = result.getString(4);
                System.out.printf("%s - %s - %s - %s\n", output1, output2, output3, output4);
            }
            System.out.println();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}