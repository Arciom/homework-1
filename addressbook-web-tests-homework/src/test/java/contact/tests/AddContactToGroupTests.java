package contact.tests;

import moduleContact.ContactData;
import moduleContact.Contacts;
import moduleGroup.GroupData;
import moduleGroup.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testBase.TestBase;

import java.security.acl.Group;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by arciom on 08.07.2017.
 */
public class AddContactToGroupTests extends TestBase{

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
              .withAddress("Minsk"), true);
    } else {
      ContactData contact= app.db().contacts().iterator().next();
      if(contact.getGroups().size() == 0) {
        contact.inGroup(groups.iterator().next());
      }
    }
  }
  @Test
  public void testAddGroups() {
    int contactId = 0;
    boolean completed = false;
    Groups beforeAddGroups = null;
    Groups beforeWithAddedGroups = null;
    Groups exitedGroups = app.db().groups();
    Contacts contacts = app.db().contacts();

    for(ContactData editedContact : contacts) {
      beforeAddGroups = editedContact.getGroups();
      GroupData newGroup =  app.contact().getGroupToAdd(exitedGroups, editedContact);
      if(newGroup != null) {
        app.contact().addContact(editedContact, newGroup);
        contactId = editedContact.getId();
        beforeWithAddedGroups = beforeAddGroups.withAdded(newGroup);
        completed = true;
        break;
      }
    }

    if (!completed) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test11").withHeader("test22")
                 .withFooter("test33"));
      Groups extendedGroups = app.db().groups();
      GroupData lastAddedGroup = extendedGroups.stream().max((g1, g2) ->
              Integer.compare(g1.getId(), g2.getId())).get();
      ContactData contact = contacts.iterator().next();
      contactId = contact.getId();
      app.contact().addContact(contact, lastAddedGroup);
      beforeWithAddedGroups = beforeAddGroups.withAdded(lastAddedGroup);
    }
    Groups groupAfter = app.db().contactById(contactId).getGroups();
    assertThat(groupAfter, equalTo(beforeAddGroups));
  }





}
