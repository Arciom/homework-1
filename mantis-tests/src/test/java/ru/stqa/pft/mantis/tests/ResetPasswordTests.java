package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer() { app.mail().start(); }

  @Test
  public void testResetPassword() throws IOException, MessagingException {

    //Администратор входит в систему
    app.resetPassword().adminLogin();

    //Администратор переходит на страницу управления пользователями
    app.resetPassword().openManageSection();
    app.resetPassword().openManageUsersSection();

    //Администратор выбирает некоторого пользователя и инициирует смену пароля (жмет Reset Password)
    UserData situatedUser = app.db().getSituatedUsers().iterator().next();
    String situatedUserEmail = situatedUser.getEmail();
    app.resetPassword().openUserByUsername(situatedUser.getUsername());
    app.resetPassword().initPasswordReset();

    //Отправляется письмо на email выбранного пользователя и получаем это письмо
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);

    //извлекаем ссылку из этого письма
    String confirmationLink = app.resetPassword().findConfirmationLink(mailMessages, situatedUserEmail);

    //переходим по ссылке и меняем пароль
    String newPassword = app.resetPassword().generatedPassword();
    app.resetPassword().finish(confirmationLink, newPassword);

    //Проверим, что пользователь может войти в систему под новым паролем
    app.resetPassword().logout();
    HttpSession session = app.newSession();
    assertTrue(session.login(situatedUser.getUsername(), newPassword));
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
