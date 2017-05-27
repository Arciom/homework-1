package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by arciom on 24.05.2017.
 */
public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void gotoGroupPage() {
    click(By.linkText("GROUPS"));
  }

  public void gotoAddNewPage() {
    click(By.linkText("ADD_NEW"));
  }

  public void gotoHome() {
    click(By.linkText("HOME"));
  }

}
