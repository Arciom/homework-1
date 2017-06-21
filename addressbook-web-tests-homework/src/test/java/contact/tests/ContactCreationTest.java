package contact.tests;

import moduleContact.ContactData;
import moduleContact.Contacts;
import org.testng.annotations.Test;
import testBase.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().home();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("aaa").
            withMiddlename("bbb").withLastname("ccc").withNickname("ddd").withtTitle("eee").
            withtCompany("LLC").withAddress("Minsk").withtGroup("test1");

    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test
  public void testBadContactCreation() {
    app.goTo().home();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("'").
            withMiddlename("bbb").withLastname("ccc").withNickname("ddd").withtTitle("eee").
            withtCompany("LLC").withAddress("Minsk").withtGroup("test1");

    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }
}

























