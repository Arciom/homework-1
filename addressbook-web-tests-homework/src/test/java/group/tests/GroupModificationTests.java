package group.tests;

import moduleGroup.GroupData;
import org.testng.annotations.Test;
import testBase.TestBase;

/**
 * Created by arciom on 24.05.2017.
 */
public class GroupModificationTests extends TestBase {
  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("A", "B", "C"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
  }

}
