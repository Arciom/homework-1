package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase {

    @Test
    public void testGroupDeletion() {
        app.getNavigatioHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deletSelectedGroup();
        app.getGroupHelper().retutrnToGroupPage();
    }
}
