package contact.tests;

import moduleContact.ContactData;
import moduleContact.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testBase.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by arciom on 24.05.2017.
 */

public class ContactDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {

    if(app.db().contacts().size() ==  0) {
      app.goTo().home();
      app.goTo().addNewPage();
      app.contact().create(new ContactData().withFirstname("aaa").
              withMiddlename("bbb").withLastname("ccc").withNickname("ddd").withTitle("eee").
              withCompany("LLC").withAddress("Minsk").withGroup("test1").withHomePhone("111").
              withMobilePhone("222").withWorkPhone("333").withEmail("test1@llc.com").
              withEmail2("test2@llc.by").withEmail3("test3@llc.org"), true);
    }
  }

  @Test
  public void testContactDeletion() {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    assertEquals(app.contact().count(), before.size() - 1);
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withOut(deletedContact)));
  }
}

















