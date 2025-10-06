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

    public void launch() {
        createFile();
        addContact();
        displayFile();
        saxParseXML();
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

    private void displayFile() {
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

    public void saxParseXML(){
        XMLParserHandler handler = new XMLParserHandler();
        SAXParser saxParser;
        List<Contact> parseContactList;
        try {
            saxParser = SAXParserFactory.newInstance().newSAXParser();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }

        try {
            saxParser.parse(fileXML, handler);
            parseContactList = handler.XMLtoList();
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(Contact c: parseContactList)
            System.out.println("Contacts from the XML: " + c.getName());
    }
}
