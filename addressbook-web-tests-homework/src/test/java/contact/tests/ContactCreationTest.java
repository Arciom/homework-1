package contact.tests;

import moduleContact.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;
import testBase.TestBase;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoHome();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().gotoAddNewPage();
    ContactData contact = new ContactData(
            "aaa", "bbb", "ccc",
            "ddd", "eee", "LLC",
            "Minsk", "test1");
    app.getContactHelper().createContact(contact, true);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);


    //созданный контакт будет имееть максимальный идентефикатор
    int max = 0;
    for(ContactData contactData : after) {
      if(contactData.getId() > max) {
        max = contactData.getId();
      }
    }

    contact.setId(max);
    //добавляем в список созданный контакт
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}

























