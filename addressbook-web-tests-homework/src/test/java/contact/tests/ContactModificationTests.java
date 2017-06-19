package contact.tests;

import moduleContact.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testBase.TestBase;
import java.util.Comparator;
import java.util.List;

/**
 * Created by arciom on 24.05.2017.
 */
public class ContactModificationTests extends TestBase {

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
  public void testContactModification() {
      List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData().withId(before.get(before.size() - 1).getId()).withFirstname("a")
    .withMiddlename("b").withLastname("c").withNickname("d").withtTitle("e").withtCompany("f").withAddress("i");

    int index = before.size() - 1;
      app.contact().modify(contact, index, false);
      List<ContactData> after = app.contact().list();
      Assert.assertEquals(after.size(), before.size());

      //удаляем из списка строку до модификации
      before.remove(index);
       // упорядочиваем списки перед сравнением
      contact.withId(after.stream().max(Comparator.comparingInt(ContactData::getId)).get().getId());
      //добавляем в список строку, которая появляется после модфикации
      before.add(contact);
      Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
      before.sort(byId);
      after.sort(byId);

      Assert.assertEquals(before, after);

  }
}






