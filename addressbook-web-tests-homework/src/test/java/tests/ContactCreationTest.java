package tests;

import module.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() {

    app.gotoAddNewPage();
    app.fillContactForm(new ContactData("aaa", "bbb", "ccc", "abc", "Hy", "LLC", "Minsk"));
    app.submitContactCreation();
    app.returnToHomePage();
  }


}
