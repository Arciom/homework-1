package contact.tests;

import moduleContact.ContactData;
import moduleGroup.GroupData;
import moduleGroup.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testBase.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by arciom on 08.07.2017.
 */
public class DeleteContactFromGroupTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if(app.db().groups().size() == 0) {
      app.group().create(new GroupData().withName("test1")
                                        .withHeader("test2")
                                        .withFooter("test3"));
    }
    app.goTo().home();
    Groups groups = app.db().groups();

    if(app.db().contacts().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("A")
              .withLastname("B")
              .inGroup(groups.iterator().next()), true);
    } else {
      ContactData contact= app.db().contacts().iterator().next();
      if(contact.getGroups().size() == 0) {
        contact.inGroup(groups.iterator().next());
      }
    }
  }

  @Test
  public void testDeleteContactFromGroup() {
    ContactData contact = app.db().contacts().iterator().next();
    Groups before = contact.getGroups();
    GroupData deleteGroup = before.iterator().next();
    app.contact().deleteFromGroup(contact, deleteGroup);
    Groups after = app.db().contactById(contact.getId()).getGroups();
    assertThat(after, equalTo(before.withOut(deleteGroup)));
  }
}
