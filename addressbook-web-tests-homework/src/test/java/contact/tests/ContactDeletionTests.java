package contact.tests;

import moduleContact.ContactData;
import org.testng.annotations.Test;
import testBase.TestBase;

/**
 * Created by arciom on 24.05.2017.
 */

public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().gotoHome();
    if(!app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoAddNewPage();
      app.getContactHelper().createContact( new ContactData(
              "aaa", "bbb", "ccc",
              "ddd", "eee", "LLC",
              "Minsk", "test1"), true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getNavigationHelper().gotoHome();
  }
}
