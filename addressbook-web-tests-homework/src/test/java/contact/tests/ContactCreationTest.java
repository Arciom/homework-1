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
    app.goTo().gotoHome();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.goTo().gotoAddNewPage();
    ContactData contact = new ContactData(
            "aaa", "bbb", "ccc",
            "ddd", "eee", "LLC",
            "Minsk", "test1");
    app.getContactHelper().createContact(contact, true);

    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

   //contact.withId(before.stream().max(Comparator.comparingInt(ContactData::getId)).get().getId());
    // упорядочиваем списки перед сравнением
    contact.setId(after.stream().max(Comparator.comparingInt(ContactData::getId)).get().getId());
    //добавляем в список созданный контакт
    before.add(contact);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    //В тестах для создания и модификации нужно упорядочивать списки перед сравнением.
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}

























