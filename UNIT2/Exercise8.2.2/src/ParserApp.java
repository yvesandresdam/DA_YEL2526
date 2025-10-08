import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ParserApp {
    private final static String file = "contacts.obj";
    private final static String fileXML = "testing.xml";
    private List<Contact> contactsList = new ArrayList<>();

    public void launch() {
        // creates a new .obj file
        createFile();
        // adds a contact to .obj file
        addContact();
        // displays the .obj contact file
        visualizeFile();
        // parses an XML file to a contacts list
        saxParseXML();
        // displays the .xml file
        visualizeParser();
        // adds the .xml file to the .obj contacts file
        XMLImport();
        // adds the .obj contacts file to the .xml file
        XMLExport();
    }

    private void createFile() {
        // creates a new file
        ObjectOutputStream output;

        try {
            output = new ObjectOutputStream(new FileOutputStream(file));
            output.writeInt(0);
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addContact() {
        // add a testing new contact
        Contact contact = new Contact();
        contact.setName("Miguel");

        ObjectOutputStream output;

        try {
            output = new ObjectOutputStream(new FileOutputStream(file));
            output.writeInt(1);
            output.writeObject(contact);
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void visualizeFile() {
        // shows the content of the contacts file
        ObjectInputStream input;
        List<Contact> contactsList = new ArrayList<>();

        try {
            input = new ObjectInputStream(new FileInputStream(file));
            int contactsNumber = input.readInt();
            for (int i = 0; i < contactsNumber; i++) {
                Contact c = (Contact) input.readObject();
                contactsList.add(c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (Contact c : contactsList)
            System.out.println("Contacts from object file: " + c.getName());
    }

    public void saxParseXML() {
        XMLParserHandler handler = new XMLParserHandler();
        SAXParser saxParser;
        try {
            saxParser = SAXParserFactory.newInstance().newSAXParser();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }

        try {
            saxParser.parse(fileXML, handler);
            contactsList = handler.XMLtoList();
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void visualizeParser() {
        int count = 0;
        for (Contact c : contactsList) {
            count++;
            System.out.printf("Contact " + count + " from the XML:\n");
            System.out.printf("Name: " + c.getName() + "\n");
            System.out.printf("Surname: " + c.getSurname() + "\n");
            System.out.printf("Email: " + c.getEmail() + "\n");
            System.out.printf("Phone: " + c.getPhone() + "\n");
            System.out.printf("Description: " + c.getDescription() + "\n\n");
        }
    }

    private void XMLImport(){

    }

    private void XMLExport(){

    }

}
