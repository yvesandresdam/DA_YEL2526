import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class ContactsObjectToXML extends DefaultHandler {
    private String tagContent = "";
    private String currentParent = "";
    private Contact contact;
    private Phone phone;
    private Email email;
    private List<Contact> contactsList = new ArrayList<>();
    private final static String XMLFile = "contactsObjectToXML.xml";
    private PrintWriter output;

    public void createFile() {
        try {
            output = new PrintWriter(new BufferedWriter(new FileWriter(XMLFile)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeTag(String textTag, boolean beginTag) {
        if (beginTag)
            output.println("<" + textTag + ">");
        else
            output.println("</" + textTag + ">");
    }

    public void writeTagContent(String content) {
        output.println(content);
    }

    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) {
        if (qName.equals("contactlist")) {
            writeTag("contactlist", true);
        }
        if (qName.equals("contact")) {
            contact = new Contact();
            email = new Email();
            phone = new Phone();
            writeTag("contact", true);
        }
        if (qName.equals("name")) {
            writeTag("name", true);
        }
        if (qName.equals("surname")) {
            writeTag("surname", true);
        }
        if (qName.equals("emails")) {
            currentParent = qName;
            writeTag("emails", true);
        }
        if (qName.equals("phones")) {
            currentParent = qName;
            writeTag("phones", true);
        }
        if (qName.equals("cell")) {
            writeTag("cell", true);
        }
        if (qName.equals("work")) {
            writeTag("work", true);
        }
        if (qName.equals("home")) {
            writeTag("home", true);
        }
    }

    public void characters(char ch[], int start, int length) {
        tagContent = new String(ch, start, length);
    }

    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("name")) {
            contact.setName(tagContent);
            writeTagContent(tagContent);
            writeTag("name", false);
        }
        if (qName.equals("surname")) {
            contact.setSurname(tagContent);
            writeTagContent(tagContent);
            writeTag("surname", false);
        }
        if (qName.equals("emails")) {
            writeTag("emails", false);
        }
        if (qName.equals("phones")) {
            writeTag("phones", false);
        }
        if (qName.equals("cell")) {
            if (currentParent.equals("phones")) {
                Phone phone = new Phone();
                phone.setCell(tagContent);
                contact.setPhone(phone);
                writeTagContent(tagContent);
                writeTag("cell", false);
            }
        }
        if (qName.equals("work")) {
            if (currentParent.equals("phones")) {
                Phone phone = new Phone();
                phone.setWork(tagContent);
                contact.setPhone(phone);
                writeTagContent(tagContent);
                writeTag("work", false);
            }
            if (currentParent.equals("emails")) {
                Email email = new Email();
                email.setWork(tagContent);
                contact.setEmail(email);
                writeTagContent(tagContent);
                writeTag("work", false);
            }
        }
        if (qName.equals("home")) {
            if (currentParent.equals("phones")) {
                Phone phone = new Phone();
                phone.setHome(tagContent);
                contact.setPhone(phone);
                writeTagContent(tagContent);
                writeTag("home", false);
            }
            if (currentParent.equals("emails")) {
                Email email = new Email();
                email.setHome(tagContent);
                contact.setEmail(email);
                writeTagContent(tagContent);
                writeTag("home", false);
            }
        }
        if (qName.equals("contact")) {
            contactsList.add(contact);
            writeTag("contact", false);
        }
        if (qName.equals("contactlist")) {
            writeTag("contactlist", false);
            output.close();
        }
    }

    public List<Contact> getContactsList() {
        return contactsList;
    }

}


