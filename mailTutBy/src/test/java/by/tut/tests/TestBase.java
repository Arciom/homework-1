package by.tut.tests;

import by.tut.applicationManager.AppManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    protected final AppManager app = new AppManager();

    @BeforeMethod
    public void setUp() throws Exception {  app.init();  }

    @AfterMethod
    public void tearDown() {
        app.stop();
    }
}
