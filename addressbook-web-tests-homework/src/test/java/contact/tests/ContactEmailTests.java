package contact.tests;

import moduleContact.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testBase.TestBase;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by arciom on 25.06.2017.
 */
public class ContactEmailTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().home();
    if(app.contact().all().size() == 0) {
      app.goTo().addNewPage();
      app.contact().create(new ContactData().withFirstname("aaa").
              withMiddlename("bbb").withLastname("ccc").withNickname("ddd").withTitle("eee").
              withCompany("LLC").withAddress("Minsk").withGroup("test1").withHomePhone("111").
              withMobilePhone("222").withWorkPhone("333").withEmail("test1@llc.com").
              withEmail2("test2@llc.by").withEmail3("test3@llc.org"), true);
    }
  }

  @Test
  public void testContactEmails() {
    app.goTo().home();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3()).stream().
            filter((s) -> !s.equals("")).collect(Collectors.joining("\n"));
  }

}
