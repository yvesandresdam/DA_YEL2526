import java.io.*;
import java.util.Scanner;

public class MatchList {
    private Contact contact;
    private final static String filepath = Files.FILEPATH;
    private boolean overwrite = false;
    private boolean repeatLoop = true;

    private void listAllMatches() {

    }

    private void addMatch() {

    }

    private void addMatch(Contact contact) {

    }

    private Contact findMatch() {
        return new Contact();
    }

    private Contact findMatchWithName(String name) {
        return new Contact();
    }

    public void launchApp() {

        // Splash Screen

        // Append / Overwrite the file

        // MAIN Loop

        // Listing Agenda Options

        // Users selection choice

        // Add a new contact

        // Find a contact

        // List all contacts

        // Write File

        // MAIN Loop Exits

        // Bye Screen

    }

    private void addContactFirstDraft() {
        // First draft of the app
        // CREATING A NEW CONTACT

        contact = new Contact();
        contact.setName("Amanda");

        ObjectOutputStream object;

        try {
            object = new ObjectOutputStream(new FileOutputStream(filepath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            object.writeObject(contact);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void listAllMatchesFirstDraft() {
        // First draft of the app
        // LISTING MATCHES
        ObjectInputStream contactsList;
        Contact contact;

        try {
            contactsList = new ObjectInputStream(new FileInputStream(new File(filepath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            contact = (Contact) contactsList.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Contact name:" + contact.getName());

    }

    private void splashScreen() {
        String splash = UI.SPLASH_SCREEN;
        System.out.println(splash);
    }

    private void overwritingContactsFile() {
        String UIoverwrite = UI.APPEND_OVERWRITE_FILE;
        System.out.println(UIoverwrite);

        Scanner scanner = new Scanner(System.in);
        String users = scanner.nextLine();
        if (users.toLowerCase().equals("y"))
            overwrite = true;
    }

    private void mainLoop() {
        while (repeatLoop) {
            // ui messages
            System.out.println(UI.OPTION_0_CONTACTS);
            System.out.println(UI.OPTION_1_CONTACTS);
            System.out.println(UI.OPTION_2_CONTACTS);
            System.out.println(UI.OPTION_3_CONTACTS);

            // condition of exit the main loop
            String message = UI.EXIT_THE_APP;
            System.out.println(message);
            Scanner scanner = new Scanner(System.in);
            String users = scanner.nextLine();
            if (users.toLowerCase().equals("y"))
                repeatLoop = false;
        }
    }
}
