package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigatioHelper().gotoGroupPage();
        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(new GroupData("1", "2", "3"));
        app.getGroupHelper().submitGroupCreation();
        app.getGroupHelper().retutrnToGroupPage();
    }
}
