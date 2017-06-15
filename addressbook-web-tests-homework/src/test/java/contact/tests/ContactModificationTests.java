package contact.tests;

import moduleContact.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;
import testBase.TestBase;
import java.util.Comparator;
import java.util.List;

/**
 * Created by arciom on 24.05.2017.
 */
public class ContactModificationTests extends TestBase {

  @Test

  public void testContactModification() {

    app.goTo().gotoHome();

    if(! app.getContactHelper().isThereAContact()) {

      app.goTo().gotoAddNewPage();

      app.getContactHelper().createContact(new ContactData(
              "aaa", "bbb", "ccc",
              "ddd", "eee", "LLC",
              "Minsk", "test1"), true);

    }
      List<ContactData> before = app.getContactHelper().getContactList();
      app.getContactHelper().selectContact(before.size() - 1);
      app.getContactHelper().initContactModification(before.size() - 1);

      ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "a", "b",

              "c", "d", "e",

              "f", "i", null);

      app.getContactHelper().fillContactForm(contact, false);
      app.getContactHelper().submitContactModification();
      app.getContactHelper().returnToHomePage();
      List<ContactData> after = app.getContactHelper().getContactList();
      Assert.assertEquals(after.size(), before.size());

      //удаляем из списка строку до модификации
      before.remove(before.size() - 1);
       // упорядочиваем списки перед сравнением
      contact.setId(after.stream().max(Comparator.comparingInt(ContactData::getId)).get().getId());
      //добавляем в список строку, которая появляется после модфикации
      before.add(contact);
      Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
      before.sort(byId);
      after.sort(byId);

      Assert.assertEquals(before, after);

  }
}






