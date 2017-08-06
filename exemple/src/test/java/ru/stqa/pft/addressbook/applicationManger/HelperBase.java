package ru.stqa.pft.addressbook.applicationManger;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HelperBase {
    protected WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    //null - оставляем в поле информацию которая там уже есть
    public void type(By locator, String text) {
        click(locator);
        if(text != null) {
            // чтобы узнать если в поле уже есть необходимая нам информация то проверку
            // то если текст не null тогда извлекаем то значение которое там храниться
            String existingText =  wd.findElement(locator).getAttribute("value");
            //тот текст который мы видим в поле является атрибктом "value", поэтому вызываем
            // команду getAttribute
            if (!text.equals(existingText)){
            wd.findElement(locator).clear();
            wd.findElement(locator).sendKeys(text);
        }}
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

}
