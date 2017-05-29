package group.tests;

import moduleGroup.GroupData;
import org.testng.annotations.Test;
import testBase.TestBase;

public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().createGroup(new GroupData("test1",
            null, null));
  }

}
