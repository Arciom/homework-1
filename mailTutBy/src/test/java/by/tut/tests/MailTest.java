package by.tut.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class MailTest extends TestBase {

    @Test
    public void testSendMail() {
        app.getNavigationHelper().clickOnWriteMail();
        app.getMailHelper().enterAddress(By.name("to"), "swert.rem@mail.ru");
        app.getMailHelper().enterHeader("Test");
        app.getMailHelper().fillMail();
        app.getNavigationHelper().gotoSended();
    }



}
