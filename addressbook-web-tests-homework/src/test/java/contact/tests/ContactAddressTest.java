package contact.tests;

import moduleContact.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testBase.TestBase;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by arciom on 26.06.2017.
 */
public class ContactAddressTest extends TestBase{
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().home();
    if(app.contact().all().size() == 0) {
      app.goTo().addNewPage();
      app.contact().create(new ContactData().withFirstname("aaa").
              withMiddlename("bbb").withLastname("ccc").withNickname("ddd").withTitle("eee").
              withCompany("LLC").withAddress("Minsk").withHomePhone("111").
              withMobilePhone("222").withWorkPhone("333").withEmail("test1@llc.com").
              withEmail2("test2@llc.by").withEmail3("test3@llc.org"), true);
    }
  }

  @Test
  public void testContactAddress() {
    app.goTo().home();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
  }
}
