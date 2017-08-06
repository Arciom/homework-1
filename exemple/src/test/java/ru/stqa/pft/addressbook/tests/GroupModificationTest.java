package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTest extends TestBase {

    @Test
    public void testModificationGroup() {
        app.getNavigatioHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(
                //null - оставляем в поле информацию которая там уже есть
                new GroupData("q", null, null));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().retutrnToGroupPage();

    }
}
