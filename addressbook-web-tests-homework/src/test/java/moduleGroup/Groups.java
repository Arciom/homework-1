package moduleGroup;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by arciom on 16.06.2017.
 */
//интерфейс ForwardingSet<> расширяет возможности Set
public class Groups extends ForwardingSet<GroupData>{

  private Set<GroupData> delegate;

  //берём множество
  public Groups(Groups groups) {
    //копируем множество из существующего множества groups переданного в
    // в параметре, сирми новое множество которое содержит теже самые элементы
    // и присваиваем это в новое множество  в качестве атрибута в новый
    // создаваемый этим конструктором объект
    this.delegate = new HashSet<GroupData>(groups.delegate);
  }

  public Groups() {
    this.delegate = new HashSet<GroupData>();
  }

  // это обязательный метод интерфейса
  @Override
  protected Set<GroupData> delegate() {
    return delegate;
  }
// что бы передовать цепочки каскад необходимо чтобы метод
// withAdded(GroupData group) передавал groups
// мы сделаем копию groups т.ч. старый объект останется неизменным
// а новый возращает новый объект с добавленной новой группой

  public Groups withAdded(GroupData group) {
    //создали копию
  Groups groups = new Groups(this);
  // в эту копию добавляем объект который передан в качестве параметра
   groups.add(group);
   return groups;
  }
//метод делает копию из которлй удалена какая то группа
  public Groups withOut(GroupData group) {
    //создали копию
    Groups groups = new Groups(this);
    // в эту копию удаляем объект который передан в качестве параметра
    groups.remove(group);
    return groups;
  }
}

























