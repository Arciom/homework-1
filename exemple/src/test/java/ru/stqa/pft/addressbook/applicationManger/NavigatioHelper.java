package ru.stqa.pft.addressbook.applicationManger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigatioHelper extends HelperBase {

    public NavigatioHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {
        click(By.linkText("GROUPS"));
    }
}
