package contact.tests;

import moduleContact.ContactData;
import moduleContact.Contacts;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testBase.TestBase;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by arciom on 24.05.2017.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().home();
    if(app.contact().all().size() == 0) {
      app.goTo().addNewPage();
      app.contact().create(new ContactData().withFirstname("aaa").
              withMiddlename("bbb").withLastname("ccc").withNickname("ddd").withtTitle("eee").
              withtCompany("LLC").withAddress( "Minsk").withtGroup("test1"), true);
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.contact().all();
    ContactData modifyContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifyContact.getId()).
            withFirstname("a").withMiddlename("b").withLastname("c").withNickname("d").
            withtTitle("e").withtCompany("f").withAddress("i");
    app.contact().modify(contact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.withOut(modifyContact).withAdded(contact)));

  }
}






