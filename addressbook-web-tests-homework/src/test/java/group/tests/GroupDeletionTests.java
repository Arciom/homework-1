package group.tests;

import moduleGroup.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testBase.TestBase;

import java.util.List;
import java.util.Set;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
  }
  @Test
  public void testGroupDeletion() {
    // заменили метод list на all
    // тип обЪектов с List на Set
    Set<GroupData> before = app.group().all();
    // before.iterator().next(); - выберет из набора любую произвольную группу
    // iterator последовательно перебирает элементы
    // next вернёт произвольный элемент множества
    // deletedGroup и надо удалять перед тем как сравнивать старое и новое множество
    // deletedGroup и надо удалять в тестируемом приложении
    // а в качестве идентификатора передавать и deletedGroup поскольку
    // он в том числе содержит идентификатор
    GroupData deletedGroup = before.iterator().next();
 //   int index = before.size() - 1;  можно удалить т.к. не используется
    // а в качестве идентификатора передавать и deletedGroup поскольку
    // он в том числе содержит идентификатор
    app.group().delete(deletedGroup);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() - 1);
    // deletedGroup и надо удалять перед тем как сравнивать старое и новое множество
    //
    before.remove(deletedGroup);
    Assert.assertEquals(before, after);

  }
}














