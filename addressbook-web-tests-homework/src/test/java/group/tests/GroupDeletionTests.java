package group.tests;

import moduleGroup.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;
import testBase.TestBase;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() {
    app.getNavigationHelper().gotoGroupPage();

    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1",
              null, null));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() - 1);
 // Лекция 4.6. Сравнение коллекций. Проверяем название внутри групп
    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);

  }

}














