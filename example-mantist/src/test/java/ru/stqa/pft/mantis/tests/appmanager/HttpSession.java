package ru.stqa.pft.mantis.tests.appmanager;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arciom on 11.07.2017.
 */
public class HttpSession {
  private CloseableHttpClient httpClient;
  private ApplicationManager app;

  public HttpSession(ApplicationManager app) {
    this.app = app;
    //когда в appmanager создаётся вызывается метод newSession() создаётся новыё экземпляр
    // помощника новый клиент - сессия (строка ниже - создание клиента)
    // который будет отпровлять запросы на сервер

    // метод setRedirectStrategy устанавливает новую стратегию перенаправления,
    // если его этот метод не установить при запросе придёт ответ 302 придется самотсоятельно
    // обрабатывать перенаправление.
    // чтобы он это делал автоматически указываем ему
    // стратегию перенаправления new LaxRedirectStrategy()
    // итак создан новый объект помещенный в  httpClient
    httpClient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
  }
  // в помошнике реализовано два метода login - выполняет вход, второй - isLoggedInAs
  // определяем, какой пользователь сейчас залогинен
  public boolean login(String username, String password) throws IOException {
    HttpPost post = new HttpPost(app.getProperty("web.baseUrl") + "/login.php");//формируем post-запрос (запрос с параметрами)

    //формируем параметры
    List<NameValuePair> params = new ArrayList<NameValuePair>();
    params.add(new BasicNameValuePair("username", username));
    params.add(new BasicNameValuePair("password", password));
    params.add(new BasicNameValuePair("secure_session", "on"));
    params.add(new BasicNameValuePair("retrun", "index.php"));

    //упаковываем параметры и помещаем в созданный запрос
    post.setEntity(new UrlEncodedFormEntity(params));
    // ответ от сервера
    CloseableHttpResponse response = httpClient.execute(post);
    String body = getTextFrom(response);

    //проверяем, действительно ли пользователь успешно вошел
    //признак этого - код страницы содержит строку, т.е. имя пользователя - <span class="user-info">%s</span>
    // текст страницы соержит вот такую строку "<span class=\"italic\">%s</span>" .
    return body.contains(String.format("<span class=\"italic\">%s</span>", username));
  }
// этотими методом можно проанализировать текст полученный от сервера
  private String getTextFrom(CloseableHttpResponse response) throws IOException{
    try{
      return EntityUtils.toString(response.getEntity());
  } finally {
       response.close();
    }
  }

  //определяем, какой пользователь сейчас залогинен
  public boolean isLoggedInAs(String username) throws IOException {
    //отправка get-запроса (параметры не передаются) просто преходим на главную страницу - "/index.php"
    HttpGet get = new HttpGet(app.getProperty("web.baseUrl") + "/index.php");
    // получаем ответ - response
    CloseableHttpResponse response = httpClient.execute(get);
    // извлекаем текст методом  getTextFrom(response);
    String body = getTextFrom(response);
    // проверяем что страница содержит необходимый элемент
    return body.contains(String.format("<span class=\"italic\">%s</span>", username));
  }
}












