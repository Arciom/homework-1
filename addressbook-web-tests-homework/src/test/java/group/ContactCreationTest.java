package group;

import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() {

    gotoAddNewPage();
    fillContactForm(new ContactData("aaa", "bbb", "ccc", "abc", "Hy", "LLC", "Minsk"));
    submitContactCreation();
    returnToHomePage();
  }


}
