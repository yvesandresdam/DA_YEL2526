import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class XMLWriter {
    private final static String xmlFile = "contactlist.xml";
    private final static String contactsFile = "contacts.obj";
    private final static String contactsToXML = "contactsParse.xml";
    private List<Contact> contactsList = new ArrayList<>();
    private List<Contact> contactsToXMLList = new ArrayList<>();

    public void launch(){
        // read the xml file
        //readXMLFile();
        // displays the contacts list
        //writeContactsList();

        // if you want to parse 'contacts.obj' to XML
        readContactsObject();
        parseContactsToXML();
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

    public void writeContactsList(){
        ObjectOutputStream output;
        int numberContacts = contactsList.size();

        try {
            output = new ObjectOutputStream(new FileOutputStream(contactsFile));
            output.writeInt(numberContacts);
            for(Contact c: contactsList){
                output.writeObject(c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readContactsObject(){
        ObjectInputStream input;
        int objectsCount;

        try {
            input = new ObjectInputStream(new FileInputStream(contactsFile));
            objectsCount = input.readInt();

            for(int i = 0; i < objectsCount; i++){
                Contact contact = (Contact) input.readObject();
                contactsToXMLList.add(contact);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void parseContactsToXML(){
        ContactsObjectToXML XMLParser = new ContactsObjectToXML();
        SAXParser saxParser;

        XMLParser.createFile();
        try {
            saxParser = SAXParserFactory.newInstance().newSAXParser();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }

        try {
            saxParser.parse(contactsToXML, XMLParser);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
