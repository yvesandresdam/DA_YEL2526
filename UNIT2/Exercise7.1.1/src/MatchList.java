import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MatchList {
    // Atributes
    private final static String filepath = MyFiles.FILE_PATH;
    private boolean flag = true;
    private int contactsCount;

    // main function
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
                findContact();

            // List all contacts
            if (usersOption.equals(OptionsType.LIST))
                listContacts();

            // MAIN Loop Exits
            flag = mainLoopCondition();
        }

        // Bye Screen
        System.out.println(UI.EXITING_THE_APP);
    }

    // displays welcom screen
    private void displaySplashScreen() {
        String splash = UI.SPLASH_SCREEN;
        System.out.println(splash);
    }

    // returns true if file already exists
    private boolean contactsFileExists() {
        File contactsFile = new File(filepath);
        return (contactsFile.exists());
    }

    // opens the file and updates the number of contacts
    private void openFile() {
        ObjectInputStream input;
        try {
            input = new ObjectInputStream(new FileInputStream(new File(filepath)));
            contactsCount = input.readInt();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // creates the file with 0 contacts
    private void createFile() {
        ObjectOutputStream output;

        try {
            output = new ObjectOutputStream(new FileOutputStream(new File(filepath)));
            contactsCount = 0;
            output.writeInt(contactsCount);
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // displays the users selection menu
    private void displayUsersMenu() {
        String usersMenu = UI.USER_SELECTION_MENU;
        String usersMenu1 = UI.USER_SELECTION_OPTION1;
        String usersMenu2 = UI.USER_SELECTION_OPTION2;
        String usersMenu3 = UI.USER_SELECTION_OPTION3;
        System.out.printf("%s\n%s\n%s\n%s\n", usersMenu, usersMenu1, usersMenu2, usersMenu3);
    }

    // navigates to the users selection
    private OptionsType setUserOption() {
        Scanner scanner = new Scanner(System.in);
        String userSelection = scanner.nextLine();
        OptionsType optionResult = OptionsType.UNKNOWN;
        boolean flag = true;
        while (flag) {
            if (userSelection.equals("1")) {
                optionResult = OptionsType.ADD;
                flag = false;
            } else if (userSelection.equals("2")) {
                optionResult = OptionsType.FIND;
                flag = false;
            } else if (userSelection.equals("3")) {
                optionResult = OptionsType.LIST;
                flag = false;
            } else {
                System.out.println(UI.INVALID_OPTION);
            }
        }
        return optionResult;
    }

    // function that adds a contact
    private void addContact() {
        // Create a new contact
        Contact contact = createContact();

        // Contacts in agenda
        List<Contact> contactlist = getAgendaContacts();
        contactsCount = contactlist.size();

        ObjectOutputStream output;

        try {
            output = new ObjectOutputStream(new FileOutputStream(new File(filepath)));
            output.writeInt(contactsCount + 1);
            for (int i = 0; i < contactsCount; i++) {
                output.writeObject(contactlist.get(i));
            }
            output.writeObject(contact);
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Contact createContact() {
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

        return contact;
    }

    // returns a list with the contact already in agenda
    private List<Contact> getAgendaContacts() {
        List<Contact> listResult = new ArrayList<>();
        ObjectInputStream input;

        try {
            input = new ObjectInputStream(new FileInputStream(new File(filepath)));
            contactsCount = input.readInt();
            for (int i = 0; i < contactsCount; i++) {
                listResult.add((Contact) input.readObject());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return listResult;
    }

    private void findContact() {
        System.out.println(UI.CONTACT_FIND_WITH_NAME);
        Scanner scanner = new Scanner(System.in);
        String findUser = scanner.nextLine();

        List<Contact> contactsList = getAgendaContacts();
        for(Contact c : contactsList){
            if(c.getName().equals(findUser))
                System.out.printf("The user is:\n%s\n%s\n%s\n%s\n%s\n", c.getName(), c.getSurname(), c.getEmail(), c.getPhone(), c.getDescription());
        }
    }

    private void listContacts() {
        ObjectInputStream contactsFile;

        try {
            contactsFile = new ObjectInputStream(new FileInputStream(new File(filepath)));
            contactsCount = contactsFile.readInt();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Contact> contactList = new ArrayList<>();

        for (int i = 0; i < contactsCount; i++) {
            try {
                Contact c = (Contact) contactsFile.readObject();
                contactList.add(c);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        for (Contact c : contactList) {
            String name = c.getName();
            String surname = c.getSurname();
            String email = c.getEmail();
            String movil = c.getPhone();
            String description = c.getDescription();

            System.out.printf("The users in the contact list are:\n%s\n%s\n%s\n%s\n%s\n", name, surname, email, movil, description);
        }
    }

    private boolean mainLoopCondition() {
        System.out.println(UI.EXIT_THE_APP);
        Scanner scanner = new Scanner(System.in);
        String userschoice = scanner.nextLine();
        return (userschoice.toLowerCase().equals("y"));
    }
}
