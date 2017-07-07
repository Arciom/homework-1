package contact.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import moduleContact.ContactData;
import moduleContact.Contacts;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testBase.TestBase;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {



  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try(BufferedReader reader = new BufferedReader(new FileReader
            (new File("src/test/resources/contacts.json")))) {
      String json = "";
      String line = reader.readLine();
      while(line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());//List<ContactData>.class
      return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader
            ("src/test/resources/contacts.xml"))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xStream = new XStream();
      xStream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
      return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromCsv() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    File photo = new File("src/test/resources/IMG_1263.JPG");
   try (BufferedReader reader = new BufferedReader(new FileReader
           ("src/test/resources/contacts.csv"))) {
     String line = reader.readLine();
     while (line != null) {
       String[] split = line.split(";");
       list.add(new Object[] {new ContactData()
               .withFirstname(split[0])
               .withMiddlename(split[1])
               .withLastname(split[2])
               .withNickname(split[3])
               .withPhotos(photo)
               .withTitle(split[4])
               .withCompany(split[5])
               .withAddress(split[6])
               .withHomePhone(split[7])
               .withMobilePhone(split[8])
               .withWorkPhone(split[9])
               .withEmail("test1@llc.by")
               .withEmail2("test2@llc.org")
               .withEmail3("test3@llc.com")
               .withGroup("test1")
       });
       line = reader.readLine();
     }
     return list.iterator();
   }
  }

  @Test(dataProvider = "validContactsFromXml")
  public void testContactCreation(ContactData contact) {
    app.goTo().home();
    Contacts before = app.db().contacts();
    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withAdded(
              contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    verifyContactListInUI();
    }


  @Test(enabled = false)
  public void testBadContactCreation() {
    app.goTo().home();
    Contacts before = app.db().contacts();
    ContactData contact = new ContactData().withFirstname("'").
            withMiddlename("bbb").withLastname("ccc").withNickname("ddd").withTitle("eee").
            withCompany("LLC").withAddress("Minsk").withGroup("test1").withHomePhone("111").withMobilePhone("222").
            withWorkPhone("333").withEmail("test1@llc.com").withEmail2("test2@llc.by").withEmail3("test3@llc.org");

    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before));
    verifyContactListInUI();
  }

//  @Test
//  public void testCurrentDir() {
//    File currentDir = new File(".");
//    System.out.println(currentDir.getAbsolutePath());
//    File photo = new File("src/test/resources/IMG_1152.JPG");
//    System.out.println(photo.getAbsolutePath());
//    System.out.println(photo.exists());
//  }

  // File photo = new File("src/test/resources/IMG_1263.JPG");
  //ContactData contact = new ContactData().withFirstname("aaa").
  //        withMiddlename("bbb").withLastname("ccc").withNickname("ddd").withPhotos(photo).withTitle("eee").
  //         withCompany("LLC").withAddress("Minsk").withGroup("test1").withHomePhone("111").
  //      withMobilePhone("222").withWorkPhone("333").withEmail("test1@llc.com").
  //         withEmail2("test2@llc.by").withEmail3("test3@llc.org");

}

























