import java.io.*;
import java.util.Scanner;

public class SimpleNotepad {

    public void launch() {
        // Splash Screen
        System.out.println(UI.WELCOME_MESSAGE);

        // Instantiate scanner, users input
        Scanner scanner = new Scanner(System.in);

        // Path of the text file
        System.out.println(UI.USERS_FILEPATH);
        String filepath;

        filepath = scanner.nextLine();

        boolean fileExists;
        File notepad = new File(filepath);
        fileExists = notepad.exists();

        boolean appendtext = true;
        if (fileExists) {
            System.out.println(UI.FILE_EXISTS);
            System.out.println(UI.OVERWRITE_FILE);
            String usersOverwrite = scanner.nextLine();
            if (usersOverwrite.toLowerCase().equals("y"))
                appendtext = false;
        }

        // boolean variables for while condition flag
        boolean flag = true;

        // Main loop of the application
        {
            // Writer and Input exception
            PrintWriter writer;
            try {
                writer = new PrintWriter(new BufferedWriter(new FileWriter(filepath, appendtext)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // loop for writing
            System.out.println(UI.USERS_EXITING_APP);
            int lineCount = 1;
            while (flag) {
                // writing users input
                String notepadText = scanner.nextLine();
                // flag condition
                if (notepadText.equals("#quit"))
                    flag = false;
                else
                    writer.printf("%d | %s\n",lineCount, notepadText);
                //writer.println(notepadText);
                lineCount++;
            }
            // closing writer
            writer.close();
        }
    }
}
