package moduleContact;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import java.io.File;

@XStreamAlias("contact")
public class ContactData {

  @XStreamOmitField
  private int id = Integer.MAX_VALUE;;
  @Expose
  private String firstname;
  @Expose
  private String middlename;
  @Expose
  private String lastname;
  @Expose
  private String nickname;
  @Expose
  private String title;
  @Expose
  private String company;
  @Expose
  private String address;
  @Expose
  private String group;
  private String allPhones;
  @Expose
  private String homePhone;
  @Expose
  private String mobilePhone;
  @Expose
  private String workPhone;
  private String allEmails;
  @Expose
  private String email;
  @Expose
  private String email2;
  @Expose
  private String email3;
  @Expose
  private File photos;

  public ContactData withId(int id) {    this.id = id;    return this;  }
  public ContactData withAddress(String address) { this.address = address;  return this;}
  public ContactData withFirstname(String firstname) {    this.firstname = firstname;   return this;}
  public ContactData withMiddlename(String middlename) {    this.middlename = middlename;   return this;}
  public ContactData withLastname(String lastname) {    this.lastname = lastname;   return this;}
  public ContactData withNickname(String nickname) {    this.nickname = nickname;   return this;}
  public ContactData withTitle(String title) {    this.title = title;  return this;}
  public ContactData withCompany(String company) {    this.company = company; return this; }
  public ContactData withGroup(String group) {    this.group = group; return this; }
  public ContactData withAllPhones(String allPhones) {  this.allPhones = allPhones;  return this;}
  public ContactData withHomePhone(String homePhone) {    this.homePhone = homePhone; return this;  }
  public ContactData withWorkPhone(String workPhone) {    this.workPhone = workPhone; return this;}
  public ContactData withMobilePhone(String mobilePhone) {    this.mobilePhone = mobilePhone;  return this;}
  public ContactData withAllEmails(String allEmails) {    this.allEmails = allEmails;  return this; }
  public ContactData withEmail(String email) {   this.email = email;    return this;  }
  public ContactData withEmail2(String email2) {  this.email2 = email2;    return this;  }
  public ContactData withEmail3(String email3) {    this.email3 = email3;    return this;  }
  public ContactData withPhotos(File photos) {    this.photos = photos;    return this;  }

  public int getId() { return id; }
  public String getFirstname() {  return firstname;  }
  public String getMiddlename() {
    return middlename;
  }
  public String getLastname() {
    return lastname;
  }
  public String getNickname() {
    return nickname;
  }
  public String getTitle() {
    return title;
  }
  public String getCompany() {
    return company;
  }
  public String getAddress() {
    return address;
  }
  public String getGroup() {    return group;  }
  public String getHomePhone() {    return homePhone;  }
  public String getMobilePhone() {    return mobilePhone;  }
  public String getWorkPhone() {    return workPhone;  }
  public String getAllPhones() {    return allPhones;  }
  public String getAllEmails() {    return allEmails;  }
  public File getPhoto() {    return photos;  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
  }

  @Override
  public int hashCode() {
    int result = firstname != null ? firstname.hashCode() : 0;
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
  }

  public String getEmail() {    return email;  }
  public String getEmail2() {    return email2;  }
  public String getEmail3() {    return email3;  }


  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", address='" + address + '\'' +
            ", group='" + group + '\'' +
            ", homePhone='" + homePhone + '\'' +
            ", mobilePhone='" + mobilePhone + '\'' +
            ", workPhone='" + workPhone + '\'' +
            ", email1='" + email + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
            '}';
  }

}
