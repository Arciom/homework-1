package by.tut.applicationManager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MailHelper extends HelperBase{



    public MailHelper(FirefoxDriver wd) {
        super(wd);
    }
    public void fillMail() {
        click(By.className("firepath-mathching-node"));
     //   click(By.xpath("//div[@id='cke_1_contents']/div/br"));
     //   click(By.id("editor1"));
        type(By.id("editor1"), "Hello");
        app.getNavigationHelper().clickOnSend();
    }

    public void enterHeader(String textHeader) {
        click(By.name("subj"));
        type(By.name("subj"), textHeader);
    }

    public void enterAddress(By locator, String address) {
        click((By.name("to")));
        type(locator, address);

    }
}
