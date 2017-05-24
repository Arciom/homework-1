package tests;

import module.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() {

    app.getNavigationHelper().gotoAddNewPage();
    app.getGroupHelper().fillContactForm(new ContactData("aaa", "bbb", "ccc", "abc", "Hy", "LLC", "Minsk"));
    app.getGroupHelper().submitContactCreation();
    app.getGroupHelper().returnToHomePage();
  }


}
