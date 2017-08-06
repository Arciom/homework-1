package by.tut.applicationManager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void gotoSended() { click((By.linkText("Отправленные4"))); }

    public void clickOnWriteMail() {
        click(By.linkText("Написать"));
    }

    public void clickOnSend() { click(By.id("nb-17")); }
}
