package contact.tests;

import moduleContact.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;
import testBase.TestBase;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().home();
    List<ContactData> before = app.contact().list();
    app.goTo().addNewPage();
    ContactData contact = new ContactData().withFirstname("aaa").
            withMiddlename("bbb").withLastname("ccc").withNickname("ddd").withtTitle("eee").
            withtCompany("LLC").withAddress( "Minsk").withtGroup("test1");

    app.contact().create(contact, true);

    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

   contact.withId(after.stream().max(Comparator.comparingInt(ContactData::getId)).get().getId());
    //добавляем в список созданный контакт
    before.add(contact);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    //В тестах для создания и модификации нужно упорядочивать списки перед сравнением.
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}

























