package andres;

public class App 
{
    public static void main( String[] args )
    {
        ConnectingPostgres connecting = new ConnectingPostgres();
        connecting.launch();
    }
}
