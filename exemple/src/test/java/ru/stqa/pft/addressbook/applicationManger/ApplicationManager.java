package ru.stqa.pft.addressbook.applicationManger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private String browser;
    WebDriver wd;

    private NavigatioHelper navigatioHelper;
    private GroupHelper groupHelper;
    private SessionHelper sessionHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {

        if (browser.equals(org.openqa.selenium.remote.BrowserType.FIREFOX)) {
            wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
        } else if (browser.equals(org.openqa.selenium.remote.BrowserType.CHROME)) {
            wd = new ChromeDriver();
        } else if(browser.equals(org.openqa.selenium.remote.BrowserType.IE)) {
            wd = new InternetExplorerDriver();
        }

        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/group.php");
        groupHelper = new GroupHelper(wd);
        navigatioHelper = new NavigatioHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login("admin", "secret");
    }

    public void stop() {
       wd.quit();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigatioHelper getNavigatioHelper() {
        return navigatioHelper;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }
}
