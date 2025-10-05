import java.io.*;
import java.util.Scanner;

public class SimpleReader {
    public final static String filepath = "Documentation/Lorem_ipsum.txt";

    public void launch() {
        // Splash Screen
        System.out.println(UI.WELCOME_TEXT);
        System.out.println(UI.WELCOME_TEXT_2);

        // Users input
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        // Buffered object which reads the file
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(filepath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // main loop of the application
        {
            boolean flag = true;
            int linesCount = 0;
            while (flag) {
                try {
                    linesCount++;
                    String result = reader.readLine();
                    // if line read is null
                    // reached end of the document
                    if (result == null) {
                        flag = false;
                        System.out.println(UI.ENDING_READING);
                        scanner.nextLine();
                    }
                    // if line count is equals 24
                    // the reader stops and ask to continue
                    else if (linesCount == 24) {
                        System.out.println(result);
                        System.out.println(UI.CONTINUE_READING);
                        scanner.nextLine();
                        linesCount = 0;
                    }
                    // else just print the line result
                    else {
                        System.out.println(result);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
