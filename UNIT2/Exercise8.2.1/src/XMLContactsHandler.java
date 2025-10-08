import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class XMLContactsHandler extends DefaultHandler {
    public String tagContent = "";
    Contact contact;
    List<Contact> contactsList = new ArrayList<>();

    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) {
        if (qName.equals("contactlist")) {
        }
        if (qName.equals("contact")) {
            contact = new Contact();
        }
        if (qName.equals("name")) {
        }
        if (qName.equals("surname")) {
        }
        if (qName.equals("emails")) {
        }
        if (qName.equals("phone")) {
        }
    }

    public void characters(char ch[], int start, int length) {
        tagContent = new String(ch, start, length);
    }

    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("name")) {
            contact.setName(tagContent);
        }
        if (qName.equals("surname")) {
            contact.setSurname(tagContent);
        }
        if (qName.equals("emails")) {
        }
        if (qName.equals("work")) {
            contact.setEmail(tagContent);
        }
        if (qName.equals("cell")) {
            contact.setPhone(tagContent);
        }
        if (qName.equals("phone")) {
        }
        if (qName.equals("contact")) {
            contactsList.add(contact);
        }
        if (qName.equals("contactlist")) {
        }
    }

    public List<Contact> getContactsList() {
        return contactsList;
    }

}
