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
        readXMLFile();
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
        for(Contact contact : contactsList)
            XMLReader.displayInfo(contact.getName(), contact.getSurname(), contact.getEmail(), contact.getPhone());
    }

    private static void displayInfo(String name, String surname, String email, String phone){
        System.out.printf("Contact:\nName:%s\nSurname:%s\nEmail:%s\nPhone:%s\n\n",name, surname, email, phone );
    }
}
