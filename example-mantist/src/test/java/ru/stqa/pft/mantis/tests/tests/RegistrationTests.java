package ru.stqa.pft.mantis.tests.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.tests.appmanager.HttpSession;
import ru.stqa.pft.mantis.tests.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public static void testRegistration() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String email = String.format("user%s@localhost.localdomain", now);
        String user = String.format("user%s", now);
        String password = "password";
        app.registration().start(user, email);
        //получаем 2 письма высланные при регистрации в Мантис
        List<MailMessage> mailMessages =app.mail().waitForMail(2, 10000);
        //извлекаем ссылку подтверждения регистрации из письма
        //(передаем список писем и адрес почты в котором надо извлечь ссылку на подтверждение
        String confiramationLink = findConfiramationLink(mailMessages, email);
        app.registration().finish(confiramationLink, password);
       assertTrue(app.newSession().login(user, password));
    }

    private static String findConfiramationLink(List<MailMessage> mailMessages, String email) {
        //список писем передаем в поток, фильтруем т.е.
        // сравниваем со вторым параметром(String email), и берем первое письмо из
        // полученного списка
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        // из текста полученного сообщения нужно извлечь ссылку
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text); //возвращает тот кусок текста который совпадает
                                                // с регулярнм выражением
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
