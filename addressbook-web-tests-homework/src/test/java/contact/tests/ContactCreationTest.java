package contact.tests;

import moduleContact.ContactData;
import org.testng.annotations.Test;
import testBase.TestBase;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() {

    app.getNavigationHelper().gotoAddNewPage();
    app.getContactHelper().fillContactForm(new ContactData(
            "aaa", "bbb", "ccc",
            "abc", "Hy", "LLC",
            "Minsk", "test1"), true);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();
  }


}
