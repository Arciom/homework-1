package by.tut.applicationManager;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class AppManager {

    FirefoxDriver wd;

    private MailHelper mailHelper;
    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;

    public void init() {
        wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wd.get("https://yandex.by/");
        mailHelper = new MailHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login("test1test1test@tut.by", "administrator");
    }

    public void stop() {
        wd.quit();
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public MailHelper getMailHelper() {
        return mailHelper;
    }
}
