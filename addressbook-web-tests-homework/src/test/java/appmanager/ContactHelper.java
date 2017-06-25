package appmanager;

import moduleContact.ContactData;
import moduleContact.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arciom on 24.05.2017.
 */
public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }
  public void returnToHomePage() {
    click(By.linkText("home page"));
  }
  public void submitContactCreation() {    click(By.name("submit"));
    //click(By.xpath("//div[@id='content']/form/input[21]"));
  }
  public void fillContactForm(ContactData contactData, boolean creation) {

    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    attach(By.name("photo"), contactData.getPhoto());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  private void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value = '" + id + "']")).click();
  }
  public void deleteSelectedContact() {
    click(By.xpath("//div/div[4]/form[2]/div[2]/input"));
    closeDialog();
  }

  public void home() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("HOME"));
  }

  public void addNewPage() {
    click(By.linkText("ADD_NEW"));
  }

  private void closeDialog() {
    wd.switchTo().alert().accept();
  }

  public void initContactModification(int id) {
   // wd.findElements(By.xpath(".//td[8]")).get(index).click();
    wd.findElement(By.cssSelector("a[href = 'edit.php?id=" + id + "']")).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void create(ContactData contact, boolean creation) {
    addNewPage();
    fillContactForm(contact, creation);
    submitContactCreation();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    initContactModification(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    contactCache = null;
    home();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count(){ return wd.findElements(By.name("selected[]")).size(); }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[contains(@name,\"entry\")]"));
    //(By.cssSelector("tr[name=entry]")) == (By.xpath("//tr[contains(@name,\"entry\")]"));
    for (WebElement element : elements) {
      String firstname = getFirstName(element);
      String lastname = getLastName(element);

      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
    return contacts;
  }

  private Contacts contactCache = null;
// простой и понятный способ реализации локаторв
//  public Set<ContactData> all(){
//    Set<ContactData> contacts = new HashSet<ContactData>();
//    List<WebElement> rows = wd.findElements(By.name("entry"));
//    for(WebElement row : rows) {
//      List<WebElement> cells = row.findElements(By.tagName("td"));
//      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
//      String lastname = cells.get(1).getText();
//      String firstname = cells.get(2).getText();
//      String address = cells.get(3).getText()
//      String[] phones = cells.get(5).getText().split("\n");
//      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
//                   .withAddress(address)withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2])));
//    }
//    return contacts;
//  }

  public Contacts all() {
    if(contactCache != null) {  return new Contacts(contactCache);    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[contains(@name,\"entry\")]"));
    //(By.cssSelector("tr[name=entry]")) == (By.xpath("//tr[contains(@name,\"entry\")]"));
    for (WebElement element : elements) {
      String firstname = getFirstName(element);
      String lastname = getLastName(element);
      String address = getAddress(element);
      String phones = getAllPhones(element);
      String emails = getEmails(element);
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withAddress(address).
                   withAllPhones(phones).withAllEmails(emails));
    }
    return new Contacts(contactCache);
  }

  private String getAddress(WebElement element) {
    return element.findElement(By.xpath("./td[4]")).getText();
  }
  private String getLastName(WebElement element) { return element.findElement(By.xpath("./td[2]")).getText();  }
  private String getFirstName(WebElement element) { return element.findElement(By.xpath("./td[3]")).getText();  }
  private String getEmails(WebElement element) { return element.findElement(By.xpath("./td[5]")).getText(); }
  private String getAllPhones(WebElement element) { return element.findElement(By.xpath("./td[6]")).getText(); }



  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address =  wd.findElement(By.name("address")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");

    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withAddress(address).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
            .withEmail(email).withEmail2(email2).withEmail3(email3);
  }

  private void initContactModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();

 //   wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
 //    wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
 //   wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }



  //  String lastname = getElementCssSelector(element, "td", 1);
  //  private String getElementCssSelector(WebElement element, String cssSelector, int cellNumber) {
  //  return element.findElements(By.cssSelector(cssSelector)).get(cellNumber).getText();

}










































