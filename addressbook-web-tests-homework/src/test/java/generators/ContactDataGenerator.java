package generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import moduleContact.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;


  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException e) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if(format.equals("csv")) {
      saveAsScv(contacts, new File(file));
    } else if(format.equals("xml")) {
      saveAsXml(contacts, new File(file));
    } else if(format.equals("json")) {
      saveAsJson(contacts, new File(file));
    }else {
      System.out.println("Unrecognized format: " + format);
    }
  }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    try (Writer writer = new FileWriter(file)){
      writer.write(json);
    }
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    try ( Writer writer = new FileWriter(file)){
      writer.write(xml);
    }
  }


  private void saveAsScv(List<ContactData> contacts, File file) throws IOException {
    try (Writer writer = new FileWriter(file)) {
      for(ContactData contact : contacts) {
        writer.write((String.format("%s; %s; %s; %s; %s; %s;  %s; %s; %s; %s; \n",
                contact.getFirstname(),
                contact.getMiddlename(),
                contact.getLastname(),
                contact.getNickname(),
                contact.getTitle(),
                contact.getCompany(),
                contact.getAddress(),
                contact.getHomePhone(),
                contact.getMobilePhone(),
                contact.getWorkPhone())));
      }
    }
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().withFirstname(String.format("Name %s", i))
                                    .withMiddlename(String.format("Middlename %s", i))
                                    .withLastname(String.format("LastName %s", i))
                                    .withNickname(String.format("Nickname %s", i))
                                    .withPhotos(new File("src/test/resources/IMG_1263.JPG"))
                                    .withTitle(String.format("Title %s", i))
                                    .withCompany(String.format("Company %s", i))
                                    .withAddress(String.format("Address %s", i))
                                    .withHomePhone(String.format("HomePhone %s", i))
                                    .withMobilePhone(String.format("MobilePhone %s", i))
                                    .withWorkPhone(String.format("WorkPhone %s", i))
                                    .withEmail("test1@llc.by")
                                    .withEmail2("test2@llc.org")
                                    .withEmail3("test3@llc.com")
                                    .withGroup("test1"));
      }
    return contacts;
  }
}














