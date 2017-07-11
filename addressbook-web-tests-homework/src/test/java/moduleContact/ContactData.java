package moduleContact;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import moduleGroup.GroupData;
import moduleGroup.Groups;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "addressbook")
@XStreamAlias("contact")
public class ContactData {

  @Id
  @Column (name= "id")
  @XStreamOmitField
  private int id = Integer.MAX_VALUE;;
  @Expose
  @Column (name = "firstname")
  private String firstname;
  @Expose
  @Column (name= "middlename")
  private String middlename;
  @Expose
  @Column (name = "lastname")
  private String lastname;
  @Expose
  @Column (name = "nickname")
  private String nickname;
  @Expose
  @Column (name = "title")
  private String title;
  @Expose
  @Column (name = "company")
  private String company;
  @Expose
  @Transient
 //@Column (name = "address")
 // @Type(type = "text")
  private String address;
//  @Expose
//  @Transient
//  private String group;
  @Transient
  private String allPhones;
  @Expose
  @Column (name = "home")
  @Type(type = "text")
  private String homePhone;
  @Expose
  @Column (name = "mobile")
  @Type(type = "text")
  private String mobilePhone;
  @Expose
  @Column (name = "work")
  @Type(type = "text")
  private String workPhone;
  @Transient
  private String allEmails;
  @Expose
  @Column (name = "email")
  @Type(type = "text")
  private String email;
  @Expose
  @Column (name = "email2")
  @Type(type = "text")
  private String email2;
  @Expose
  @Column (name = "email3")
  @Type(type = "text")
  private String email3;
  @Expose
  @Column (name = "photo")
  @Type(type = "text")
  private String photos;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name="address_in_groups", joinColumns = @JoinColumn(name = "id")
            ,inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();

  public ContactData withId(int id) {    this.id = id;    return this;  }
  public ContactData withAddress(String address) { this.address = address;  return this;}
  public ContactData withFirstname(String firstname) {    this.firstname = firstname;   return this;}
  public ContactData withMiddlename(String middlename) {    this.middlename = middlename;   return this;}
  public ContactData withLastname(String lastname) {    this.lastname = lastname;   return this;}
  public ContactData withNickname(String nickname) {    this.nickname = nickname;   return this;}
  public ContactData withTitle(String title) {    this.title = title;  return this;}
  public ContactData withCompany(String company) {    this.company = company; return this; }
 // public ContactData withGroup(String group) {    this.group = group; return this; }
  public ContactData withAllPhones(String allPhones) {  this.allPhones = allPhones;  return this;}
  public ContactData withHomePhone(String homePhone) {    this.homePhone = homePhone; return this;  }
  public ContactData withWorkPhone(String workPhone) {    this.workPhone = workPhone; return this;}
  public ContactData withMobilePhone(String mobilePhone) {    this.mobilePhone = mobilePhone;  return this;}
  public ContactData withAllEmails(String allEmails) {    this.allEmails = allEmails;  return this; }
  public ContactData withEmail(String email) {   this.email = email;    return this;  }
  public ContactData withEmail2(String email2) {  this.email2 = email2;    return this;  }
  public ContactData withEmail3(String email3) {    this.email3 = email3;    return this;  }
  public ContactData withPhotos(File photos) {    this.photos = photos.getPath();    return this;  }

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
//  public String getGroup() {    return group;  }
  public Groups getGroups() {  return new Groups(groups); }
  public String getHomePhone() {    return homePhone;  }
  public String getMobilePhone() {    return mobilePhone;  }
  public String getWorkPhone() {    return workPhone;  }
  public String getAllPhones() {    return allPhones;  }
  public String getAllEmails() {    return allEmails;  }
  public File getPhoto() {    return new File(photos);  }
  public String getEmail() {    return email;  }
  public String getEmail2() {    return email2;  }
  public String getEmail3() {    return email3;  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    if (middlename != null ? !middlename.equals(that.middlename) : that.middlename != null) return false;
    if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
    if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
    if (title != null ? !title.equals(that.title) : that.title != null) return false;
    if (company != null ? !company.equals(that.company) : that.company != null) return false;
    if (homePhone != null ? !homePhone.equals(that.homePhone) : that.homePhone != null) return false;
    if (mobilePhone != null ? !mobilePhone.equals(that.mobilePhone) : that.mobilePhone != null) return false;
    if (workPhone != null ? !workPhone.equals(that.workPhone) : that.workPhone != null) return false;
    if (email != null ? !email.equals(that.email) : that.email != null) return false;
    if (email2 != null ? !email2.equals(that.email2) : that.email2 != null) return false;
    return email3 != null ? email3.equals(that.email3) : that.email3 == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (middlename != null ? middlename.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
    result = 31 * result + (title != null ? title.hashCode() : 0);
    result = 31 * result + (company != null ? company.hashCode() : 0);
    result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
    result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
    result = 31 * result + (workPhone != null ? workPhone.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (email2 != null ? email2.hashCode() : 0);
    result = 31 * result + (email3 != null ? email3.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", address='" + address + '\'' +
           // ", group='" + group + '\'' +
            ", homePhone='" + homePhone + '\'' +
            ", mobilePhone='" + mobilePhone + '\'' +
            ", workPhone='" + workPhone + '\'' +
            ", email1='" + email + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
            '}';
  }

  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }
}
