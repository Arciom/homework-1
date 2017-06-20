package contact.tests;

import moduleContact.ContactData;
import moduleContact.Contacts;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import testBase.TestBase;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().home();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("aaa").
            withMiddlename("bbb").withLastname("ccc").withNickname("ddd").withtTitle("eee").
            withtCompany("LLC").withAddress( "Minsk").withtGroup("test1");

    app.contact().create(contact, true);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}

























