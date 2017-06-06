package contact.tests;

import moduleContact.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;
import testBase.TestBase;

import java.util.List;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoHome();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().gotoAddNewPage();
    app.getContactHelper().createContact(new ContactData(
            "aaa", "bbb", "ccc",
            "ddd", "eee", "LLC",
            "Minsk", "test1"), true);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
  }
}

























