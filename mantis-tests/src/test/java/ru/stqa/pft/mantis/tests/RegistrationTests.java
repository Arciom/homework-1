package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by arciom on 11.07.2017.
 */
public class RegistrationTests extends TestBase{

  @Test
  public void Registration() {
    app.registration().start("user1", "user1@localhost.localdomain");
  }
}
