package contact.tests;

import moduleContact.ContactData;
import moduleContact.Contacts;
import org.testng.annotations.Test;
import testBase.TestBase;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().home();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/IMG_1263.JPG");
    ContactData contact = new ContactData().withFirstname("aaa").
            withMiddlename("bbb").withLastname("ccc").withNickname("ddd").withPhotos(photo).withTitle("eee").
            withCompany("LLC").withAddress("Minsk").withGroup("test1").withHomePhone("111").
            withMobilePhone("222").withWorkPhone("333").withEmail("test1@llc.com").
            withEmail2("test2@llc.by").withEmail3("test3@llc.org");

    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testBadContactCreation() {
    app.goTo().home();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("'").
            withMiddlename("bbb").withLastname("ccc").withNickname("ddd").withTitle("eee").
            withCompany("LLC").withAddress("Minsk").withGroup("test1").withHomePhone("111").withMobilePhone("222").
            withWorkPhone("333").withEmail("test1@llc.com").withEmail2("test2@llc.by").withEmail3("test3@llc.org");

    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }

//  @Test
//  public void testCurrentDir() {
//    File currentDir = new File(".");
//    System.out.println(currentDir.getAbsolutePath());
//    File photo = new File("src/test/resources/IMG_1152.JPG");
//    System.out.println(photo.getAbsolutePath());
//    System.out.println(photo.exists());
//  }
}

























