package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static javafx.scene.input.KeyCode.O;

public class SoapHelper {

  private final ApplicationManager app;

  public SoapHelper(ApplicationManager app) {
    this.app = app;
  }
//метод getProjects возвращает множество модельных объектов
  public Set<Project> getProjects() throws RemoteException, MalformedURLException, ServiceException {
    //создаём соеденение
    MantisConnectPortType mc = getMantisConnect();
    ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
    return Arrays.asList(projects).stream().map((p) -> new Project()
            .withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());

  }

  public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
    //создаём соеденение
    MantisConnectPortType mc = getMantisConnect();
    String[] categories = mc.mc_project_get_categories("administrator", "root", BigInteger.valueOf(issue.getProject().getId()));
    IssueData issueData = new IssueData();
    issueData.setSummary(issue.getSummary());
    issueData.setDescription(issue.getDescription());
    issueData.setProject(new ObjectRef(
            BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
    issueData.setCategory(categories[0]);
    // присваиваем идентефикатор созданного  баг репорта в переменную
    BigInteger issueId = mc.mc_issue_add("administrator", "root", issueData);
    // опять создаём запрос и передаём имя пользователя и пароль
    IssueData createdIssueData = mc.mc_issue_get("administrator", "root", issueId);
    // перобразуем в модельный объект
    return new Issue().withId(createdIssueData.getId().intValue())
            .withSummary(createdIssueData.getSummary())
            .withDescription(createdIssueData.getDescription())
            .withProject(new Project().withId(createdIssueData.getProject().getId().intValue())
                                      .withName(createdIssueData.getProject().getName()));
  }

  //создаём соеденение
  public MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
    // return new MantisConnectLocator().getMantisConnectPort(new URL("http://localhost/mantisbt-1.2.19/api/soap/tmantisconnect.php"));
    // путь к сайту прописан в local.properties
    return new MantisConnectLocator().getMantisConnectPort(new URL(app.getProperty("web.URL")));
  }

  public String getIssueStatus(int issueId) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    return mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"),
                            BigInteger.valueOf(issueId)).getStatus().getName();


  }}
