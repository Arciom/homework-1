package ru.stqa.pft.mantis.tests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.tests.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase {

    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();
        //проверяем, что пользователь действительно залогинелся,
        // т.е. на стронице появился нужный текст
        assertTrue(session.login("administrator", "root"));
        //проверяем, какой пользователь сейчас залогинен
        assertTrue(session.isLoggedInAs("administrator"));
    }
}
