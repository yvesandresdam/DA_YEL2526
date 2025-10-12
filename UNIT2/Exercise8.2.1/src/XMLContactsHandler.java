import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class XMLContactsHandler extends DefaultHandler {
    private String tagContent = "";
    private String currentParent = "";
    private Contact contact;
    private Phone phone;
    private Email email;
    private List<Contact> contactsList = new ArrayList<>();

    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) {
        if (qName.equals("contactlist")) {
        }
        if (qName.equals("contact")) {
            contact = new Contact();
            email = new Email();
            phone = new Phone();
        }
        if (qName.equals("name")) {
        }
        if (qName.equals("surname")) {
        }
        if (qName.equals("emails")) {
            currentParent = qName;
        }
        if (qName.equals("phones")) {
            currentParent = qName;
        }
        if (qName.equals("cell")) {
        }
        if (qName.equals("work")) {
        }
        if (qName.equals("home")) {
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
        if (qName.equals("phones")) {
        }
        if (qName.equals("cell")) {
            if(currentParent.equals("phones")){
                Phone phone = new Phone();
                phone.setCell(tagContent);
                contact.setPhone(phone);
            }
        }
        if (qName.equals("work")) {
            if(currentParent.equals("phones")){
                Phone phone = new Phone();
                phone.setWork(tagContent);
                contact.setPhone(phone);
            }
            if(currentParent.equals("emails")){
                Email email = new Email();
                email.setWork(tagContent);
                contact.setEmail(email);
            }
        }
        if (qName.equals("home")) {
            if(currentParent.equals("phones")){
                Phone phone = new Phone();
                phone.setHome(tagContent);
                contact.setPhone(phone);
            }
            if(currentParent.equals("emails")){
                Email email = new Email();
                email.setHome(tagContent);
                contact.setEmail(email);
            }
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
