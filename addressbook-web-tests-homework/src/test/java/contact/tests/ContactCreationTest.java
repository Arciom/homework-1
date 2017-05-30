package contact.tests;

import moduleContact.ContactData;
import org.testng.annotations.Test;
import testBase.TestBase;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() {

    app.getNavigationHelper().gotoAddNewPage();
    app.getContactHelper().createContact(new ContactData(
            "aaa", "bbb", "ccc",
            "ddd", "eee", "LLC",
            "Minsk", "test1"), true);
  }
}
