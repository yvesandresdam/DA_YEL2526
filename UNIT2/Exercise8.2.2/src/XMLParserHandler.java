import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class XMLParserHandler extends DefaultHandler {
    public String tagContent;
    public List<Contact> contactList = new ArrayList<>();
    public Contact contact = new Contact();

    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) {
        if (qName.equals("contactlist")) {
            System.out.println("Starting tag: 'contactlist'");
        }
        if (qName.equals("contact")) {
            System.out.println("Starting tag: 'contact'");
        }
        if (qName.equals("name")) {
            System.out.println("Starting tag: 'name'");
        }
    }

    public void characters( char ch[], int start, int length ) {
        tagContent = new String( ch, start, length );
    }

    public void endElement(String uri, String localName, String qName) {

        if (qName.equals("name")) {
            System.out.println("Closing tag 'contact'");
            contact.setName(tagContent);
        }
        if (qName.equals("contact")) {
            System.out.println("Closing tag 'contact'");
        }
        if (qName.equals("contactlist")) {
            System.out.println("Closing tag 'contactlist'");
            contactList.add(contact);
        }

    }

    public List<Contact> XMLtoList(){
        return contactList;
    }
}
