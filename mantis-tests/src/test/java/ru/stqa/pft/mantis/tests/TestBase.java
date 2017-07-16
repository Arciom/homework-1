package ru.stqa.pft.mantis.tests;

import org.hibernate.service.spi.ServiceException;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.model.MailMessage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php")
            ,"config_inc.php"
            ,"config_inc.php.back");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
        app.ftp().restore("config_inc.php.back", "config_inc.php");
        app.stop();
  }

  public boolean isIssueOpen(int issueId) throws IOException, ServiceException, javax.xml.rpc.ServiceException {

    String issueStatus = app.soap().getIssueStatus(issueId);
    if (issueStatus.equals("resolved") || issueStatus.equals("closed")) {
      return false;
    }
      return true;
  }

  public void skipIfNotFixed(int issueId) throws IOException, ServiceException, javax.xml.rpc.ServiceException {

    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}














