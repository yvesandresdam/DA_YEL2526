import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLReader {
    private final static String xmlFile = "contactlist.xml";
    private List<Contact> contactsList = new ArrayList<>();

    public void launch(){
        // read the xml file
        readXMLFile();
        // displays the contacts list
        displayContactsList();
    }

    private void readXMLFile(){
        XMLContactsHandler handler;
        SAXParser saxParse;
        try {
            handler = new XMLContactsHandler();
            saxParse = SAXParserFactory.newInstance().newSAXParser();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }

        try {
            saxParse.parse(xmlFile, handler);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        contactsList = handler.getContactsList();
    }

    private void displayContactsList(){
        int count = 0;
        for(Contact contact : contactsList) {
            count++;
            XMLReader.displayInfo(count, contact.getName(), contact.getSurname(), contact.getEmail(), contact.getPhone());
        }
    }

    private static void displayInfo(int count, String name, String surname, Email email, Phone phone){
        System.out.printf("Contact number: %s\n", count);
        System.out.printf("Contact name: %s\n", name);
        System.out.printf("Contact surname: %s\n", surname);
        System.out.printf("Contact email 'home': %s\n", email.getHome());
        System.out.printf("Contact email 'work': %s\n", email.getWork());
        System.out.printf("Contact phone 'cell': %s\n", phone.getCell());
        System.out.printf("Contact phone 'work': %s\n", phone.getWork());
        System.out.printf("Contact phone 'home': %s\n\n", phone.getHome());
    }
}
