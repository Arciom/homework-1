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
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
      app.goTo().home();
    if(app.db().contacts().size() ==  0) {
      app.goTo().addNewPage();
      app.contact().create(new ContactData().withFirstname("aaa").
              withMiddlename("bbb").withLastname("ccc").withNickname("ddd").withTitle("eee").
              withCompany("LLC").withAddress("Minsk").withHomePhone("111").
              withMobilePhone("222").withWorkPhone("333").withEmail("test1@llc.com").
              withEmail2("test2@llc.by").withEmail3("test3@llc.org").
              withPhotos(new java.io.File("src/test/resources/IMG_1263.JPG"))
              , true);
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifyContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifyContact.getId()).
            withFirstname("a").withMiddlename("b").withLastname("c").withNickname("d").
            withTitle("e").withCompany("f").withAddress("i").withHomePhone("111").
            withMobilePhone("222").withWorkPhone("333").withEmail("test1@llc.com").
            withEmail2("test2@llc.by").withEmail3("test3@llc.org").
            withPhotos(new java.io.File("src/test/resources/IMG_1263.JPG"));

    app.goTo().home();
    app.contact().modify(contact);
    assertEquals(app.contact().count(), before.size());
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withOut(modifyContact).withAdded(contact)));
    verifyContactListInUI();
  }
}






