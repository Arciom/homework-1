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
  public void submitContactCreation() {
    click(By.name("submit"));
    //click(By.xpath("//div[@id='content']/form/input[21]"));
  }
  public void fillContactForm(ContactData contactData, boolean creation) {

    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());

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

  public Contacts all() {
    if(contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[contains(@name,\"entry\")]"));
    //(By.cssSelector("tr[name=entry]")) == (By.xpath("//tr[contains(@name,\"entry\")]"));
    for (WebElement element : elements) {
      String firstname = getFirstName(element);
      String lastname = getLastName(element);
      String address = getAddress(element);
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withAddress(address));
    }
    return new Contacts(contactCache);
  }

  private String getAddress(WebElement element) {
    return element.findElement(By.xpath("./td[4]")).getText();
  }

  private String getLastName(WebElement element) {
    return element.findElement(By.xpath("./td[2]")).getText();
  }

  private String getFirstName(WebElement element) { return element.findElement(By.xpath("./td[3]")).getText();  }


  //  String lastname = getElementCssSelector(element, "td", 1);
  //  private String getElementCssSelector(WebElement element, String cssSelector, int cellNumber) {
  //  return element.findElements(By.cssSelector(cssSelector)).get(cellNumber).getText();

}










































