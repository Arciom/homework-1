package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

/**
 * Created by arciom on 16.07.2017.
 */
public class RestAssuredTests extends TestBase{

  @BeforeClass
  public void init() throws IOException {
    RestAssured.authentication = RestAssured.
            basic("LSGjeU4yP1X493ud1hNniA==", "");
  }

  @Test
  public void testCreateIssue() throws IOException {
    skipIfNotFixed(1);
    Set<Issue> oldIssues = getIssues();
    Issue newIssue = new Issue().withSubject("Test issue").withDescription("New test issue");
    int issueId = createIssue(newIssue);
    Set<Issue> newIssues = getIssues();
    oldIssues.add(newIssue.withId(issueId));
    Assert.assertEquals(newIssues, oldIssues);
  }

  private Set<Issue> getIssues() throws IOException {
//    String json  = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json"))
//            .returnContent().asString();
    String json = RestAssured.get("http://demo.bugify.com/api/issues.json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
  }

//  private Executor getExecutor() {
//    return Executor.newInstance()
//            .auth("LSGjeU4yP1X493ud1hNniA==", "");
//  }

  private int createIssue(Issue newIssue) throws IOException {
//   String json = getExecutor().execute(Request.Post("http://demo.bugify.com/api/issues.json")
//            .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
//             new BasicNameValuePair("description", newIssue.getDescription())))
//            .returnContent().asString();
    String json = RestAssured.given()
                       .parameter("subject", newIssue.getSubject())
                       .parameter("description", newIssue.getDescription())
                       .post("http://demo.bugify.com/api/issues.json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }
}
