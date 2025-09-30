import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SimpleNotepad {

    public void launch() {
        // Splash Screen
        System.out.println("[WELCOME MESSAGE]");

        // Instantiate scanner, users input
        Scanner scanner = new Scanner(System.in);

        // boolean variables for while condition flag
        boolean flag = true;

        // program variables
        boolean appendtext = true;

        // Overwrite conditions
        System.out.println("[OVERWRITE MESSAGE]");
        String overwrite = scanner.nextLine();
        if (overwrite.equals("Y") || overwrite.equals("y"))
            appendtext = false;

        // Main loop of the application
        {
            // Writer and Input exception
            PrintWriter writer;
            try {
                writer = new PrintWriter(new FileWriter("SimpleNotepad.txt", appendtext));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // loop for writing
            while (flag) {
                // writing users input
                String notepadText = scanner.nextLine();
                writer.write(notepadText);

                // flag condition
                if (notepadText.equals(""))
                    flag = false;
            }

            // closing writer
            writer.close();
        }
    }
}
