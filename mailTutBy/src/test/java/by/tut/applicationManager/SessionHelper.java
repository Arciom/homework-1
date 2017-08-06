package by.tut.applicationManager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SessionHelper extends HelperBase{

    public SessionHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void login(String yourEmail, String password) {
        click(By.linkText("Почта"));
        click(By.name("login"));
        type(By.name("login"), yourEmail);
        click(By.name("passwd"));
        type(By.name("passwd"),password );
        click(By.xpath("//div[@class='new-left']//button[.='Войти']"));
    }

}
