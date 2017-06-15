package group.tests;

import moduleGroup.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;
import testBase.TestBase;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    // заменили метод list на all
    Set<GroupData> before = app.group().all();
    GroupData group = new GroupData().withName("test2");
    app.group().create(group);
    // заменили метод list на all
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() + 1);

// новой добавленой группе присваиваем правильный иденификатор; в скобках должен вычисляться
// максимальный идентификатор - делаем это так: берём коллекцию которая содержит
// группу с уже известными идентификаторами, превращаем её в поток, ав потоке исчем макс. элем-т
// с помощью компоратора и сравнивает группы по их идентефикаторам, а  сейчас другой способ
// в котором поток обЪектов GroupDate превратим в поток идентификаторов
// т.е. чисел при помощи функции mapToInt()
// в качестве параметра принимает группу, а в качестве результата выдает
// идентефикатор этой группы. т.е. преобразует объект в число. т.о мы
// получили поток чисел у которого есть функция max который сортирует,
// а затем метод getAsInt преобразует результат в целое число
    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(group);
//сортировать множества нет необходимости
//    Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
//    before.sort(byId);
//    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
