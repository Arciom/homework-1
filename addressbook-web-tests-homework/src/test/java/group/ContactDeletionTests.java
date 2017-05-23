package group;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    gotoHomePage();
    selectContact();
    deleteSelectedContact();
  }
}
