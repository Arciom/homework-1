package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by arciom on 24.05.2017.
 */
public class NavigationHelper {
  private FirefoxDriver wd;

  public NavigationHelper(FirefoxDriver wd) {
    this.wd = wd;
  }

  public void gotoGroupPage() {

    wd.findElement(By.linkText("GROUPS")).click();
  }

  public void gotoAddNewPage() {
    wd.findElement(By.linkText("ADD_NEW")).click();
  }
 }
