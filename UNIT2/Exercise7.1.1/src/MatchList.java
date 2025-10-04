import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MatchList {
    // Atributes
    private Contact contact;
    private final static String filepath = MyFiles.FILE_PATH;
    private boolean flag = true;
    private int contactsNumber = 0;


    public void launchApp() {
        // Splash Screen
        displaySplashScreen();

        // Open / Create the contacts file
        boolean fileExists = contactsFileExists();
        if (fileExists)
            openFile();
        else
            createFile();

        // MAIN Loop
        while (flag) {
            // Users menu - Listing Agenda Options
            displayUsersMenu();

            // Users selection choice
            OptionsType usersOption = setUserOption();

            // Add a new contact
            if (usersOption.equals(OptionsType.ADD))
                addContact();

            // Find a contact
            if (usersOption.equals(OptionsType.FIND))
                findContact(new Contact());

            // List all contacts
            if (usersOption.equals(OptionsType.LIST))
                listContacts();

            // MAIN Loop Exits
            flag = mainLoopCondition();
        }

        // Bye Screen
        System.out.println(UI.EXITING_THE_APP);
    }

    private void displaySplashScreen() {
        String splash = UI.SPLASH_SCREEN;
        System.out.println(splash);
    }

    private boolean contactsFileExists() {
        File contactsFile = new File(filepath);
        return (contactsFile.exists());
    }

    private void openFile() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filepath));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            contactsNumber = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createFile() {
        PrintWriter writer;

        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(filepath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        writer.println(0);

    }

    private void displayUsersMenu() {
        String usersMenu = UI.USER_SELECTION_MENU;
        String usersMenu1 = UI.USER_SELECTION_OPTION1;
        String usersMenu2 = UI.USER_SELECTION_OPTION2;
        String usersMenu3 = UI.USER_SELECTION_OPTION3;
        System.out.printf("%s\n%s\n%s\n%s\n", usersMenu, usersMenu1, usersMenu2, usersMenu3);
    }

    private OptionsType setUserOption() {
        Scanner scanner = new Scanner(System.in);
        String userSelection = scanner.nextLine();
        boolean flag = true;
        while (flag) {
            if (userSelection.equals("1"))
                return OptionsType.ADD;
            else if (userSelection.equals("2"))
                return OptionsType.FIND;
            else if (userSelection.equals("3"))
                return OptionsType.LIST;
            System.out.println(UI.INVALID_OPTION);
        }
        return OptionsType.UNKNOWN;
    }

    private boolean mainLoopCondition() {
        System.out.println(UI.EXIT_THE_APP);
        Scanner scanner = new Scanner(System.in);
        String userschoice = scanner.nextLine();
        return (userschoice.toLowerCase().equals("y"));
    }

    private void addContact() {
        Contact contact = new Contact();
        Scanner scanner = new Scanner(System.in);

        System.out.println(UI.INSERT_NAME);
        String name = scanner.nextLine();
        contact.setName(name);

        System.out.println(UI.INSERT_SURNAME);
        String surname = scanner.nextLine();
        contact.setSurname(surname);

        System.out.println(UI.INSERT_EMAIL);
        String email = scanner.nextLine();
        contact.setEmail(email);

        System.out.println(UI.INSERT_MOVIL);
        String movil = scanner.nextLine();
        contact.setPhone(movil);

        System.out.println(UI.INSERT_DESCRIPTION);
        String description = scanner.nextLine();
        contact.setDescription(description);

        ObjectOutputStream newContact;

        try {
            newContact = new ObjectOutputStream(new FileOutputStream(filepath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            newContact.write(contactsNumber++);
            newContact.writeObject(contact);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void findContact(Contact contact) {
        ObjectInputStream contactsFile;

        try {
            contactsFile = new ObjectInputStream(new FileInputStream(filepath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int contactsNumber;

        try {
            contactsNumber = contactsFile.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Contact> contactList = new ArrayList<>();

        for(int i = 0; i < contactsNumber; i++){
            try {
                Contact c = (Contact) contactsFile.readObject();
                if(contact.equals(c))
                    System.out.println(UI.CONTACT_FOUND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        for(Contact c : contactList){
            String name = c.getName();
            String surname = c.getSurname();
            String email = c.getEmail();
            String movil = c.getPhone();
            String description = c.getDescription();

            System.out.printf("The users in the contact list are:\n%s\n%s\n%s\n%s\n%s", name, surname, email, movil, description);
        }


    }

    private void listContacts() {
        ObjectInputStream contactsFile;

        try {
            contactsFile = new ObjectInputStream(new FileInputStream(filepath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int contactsNumber;

        try {
            contactsNumber = contactsFile.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Contact> contactList = new ArrayList<>();

        for(int i = 0; i < contactsNumber; i++){
            try {
                Contact c = (Contact) contactsFile.readObject();
                contactList.add(c);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        for(Contact c : contactList){
            String name = c.getName();
            String surname = c.getSurname();
            String email = c.getEmail();
            String movil = c.getPhone();
            String description = c.getDescription();

            System.out.printf("The users in the contact list are:\n%s\n%s\n%s\n%s\n%s", name, surname, email, movil, description);
        }
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
}
