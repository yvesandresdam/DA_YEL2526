package andres;

import java.sql.SQLException;

public class App
{
    public static void main( String[] args )
    {
        ConnectingPostgres connecting = new ConnectingPostgres();
        try {
            connecting.launch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
