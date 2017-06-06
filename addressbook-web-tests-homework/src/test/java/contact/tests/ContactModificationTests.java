package contact.tests;

import moduleContact.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;
import testBase.TestBase;

import java.util.List;

/**
 * Created by arciom on 24.05.2017.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHome();
    if(! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoAddNewPage();
      app.getContactHelper().createContact(new ContactData(
              "aaa", "bbb", "ccc",
              "ddd", "eee", "LLC",
              "Minsk", "test1"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("a", "b",
            "c", "d", "e",
            "f", "i", null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());
  }
}
