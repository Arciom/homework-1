package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import java.io.IOException;

import static org.testng.Assert.assertTrue;

/**
 * Created by arciom on 11.07.2017.
 */
public class LoginTests extends TestBase{

  @Test
  public void testLogin() throws IOException {
    HttpSession session = app.newSession();
    //проверяем, что пользователь действительно залогинился
    assertTrue(session.login("administrator", "root"));
    assertTrue(session.isLoggedInAs("administrator"));
  }
}
