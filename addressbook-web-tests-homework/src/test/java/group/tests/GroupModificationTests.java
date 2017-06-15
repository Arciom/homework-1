package group.tests;

import moduleGroup.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testBase.TestBase;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Created by arciom on 24.05.2017.
 */
public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupModification() {
    Set<GroupData> before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
   // int index = before.size() - 1;
    GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("test1").
            withHeader("test2").withFooter("test3");
    app.group().modify(group);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() );

    before.remove(modifiedGroup);
    before.add(group);
    //сортировка не нужна
   // Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
   // before.sort(byId);
   // after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
