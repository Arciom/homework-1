package appmanager;

import moduleContact.ContactData;
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
    click(By.xpath("//div[@id='content']/form/input[21]"));
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

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath("//div/div[4]/form[2]/div[2]/input"));
    closeDialog();
  }

  private void closeDialog() {
    wd.switchTo().alert().accept();
  }

  public void initContactModification(int index) {
    wd.findElements(By.xpath(".//td[8]")).get(index).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void createContact(ContactData contact, boolean creation) {

    fillContactForm(contact, creation);
    submitContactCreation();
    returnToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

 // public int getContactCount() {
 //   return wd.findElements(By.name("selected[]")).size();
//  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[contains(@name,\"entry\")]"));
    //(By.cssSelector("tr[name=entry]")) == (By.xpath("//tr[contains(@name,\"entry\")]"));
    for (WebElement element : elements) {
      String firstname = getFirstName(element);
      String lastname = getLastName(element);
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      ContactData contact = new ContactData(id, firstname, null,
              lastname, null, null, null,
              null, null);
      contacts.add(contact);
    }
    return contacts;
  }

  private String getLastName(WebElement element) {
    return element.findElement(By.xpath("./td[2]")).getText();
  }

  private String getFirstName(WebElement element) {
    return element.findElement(By.xpath("./td[3]")).getText();
  }
  //  String lastname = getElementCssSelector(element, "td", 1);
  //  private String getElementCssSelector(WebElement element, String cssSelector, int cellNumber) {
  //  return element.findElements(By.cssSelector(cssSelector)).get(cellNumber).getText();

}










































