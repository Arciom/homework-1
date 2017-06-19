package contact.tests;

import moduleContact.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testBase.TestBase;

import java.util.List;

/**
 * Created by arciom on 24.05.2017.
 */

public class ContactDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().home();
    if(app.contact().list().size() == 0) {
      app.goTo().addNewPage();
      app.contact().create(new ContactData().withFirstname("aaa").
              withMiddlename("bbb").withLastname("ccc").withNickname("ddd").withtTitle("eee").
              withtCompany("LLC").withAddress( "Minsk").withtGroup("test1"), true);
    }
  }

  @Test
  public void testContactDeletion() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(index);
    Assert.assertEquals(before, after);

  }


}

















