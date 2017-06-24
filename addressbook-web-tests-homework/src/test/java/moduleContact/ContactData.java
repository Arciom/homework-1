package moduleContact;

public class ContactData {

  private int id = Integer.MAX_VALUE;;
  private String firstname;
  private String middlename;
  private String lastname;
  private String nickname;
  private String title;
  private String company;
  private String address;
  private String group;
  private String homePhone;
  private String mobilePhone;
  private String workPhone;

  public ContactData withId(int id) {    this.id = id;    return this;  }
  public ContactData withAddress(String address) { this.address = address;  return this;}
  public ContactData withFirstname(String firstname) {    this.firstname = firstname;   return this;}
  public ContactData withMiddlename(String middlename) {    this.middlename = middlename;   return this;}
  public ContactData withLastname(String lastname) {    this.lastname = lastname;   return this;}
  public ContactData withNickname(String nickname) {    this.nickname = nickname;   return this;}
  public ContactData withTitle(String title) {    this.title = title;  return this;}
  public ContactData withCompany(String company) {    this.company = company; return this; }
  public ContactData withGroup(String group) {    this.group = group; return this; }
  public ContactData withHomePhone(String homePhone) {    this.homePhone = homePhone; return this;  }
  public ContactData withWorkPhone(String workPhone) {    this.workPhone = workPhone; return this;}
  public ContactData withMobilePhone(String mobilePhone) {    this.mobilePhone = mobilePhone;  return this;}



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
  public String getGroup() {
    return group;
  }
  public String getHomePhone() {    return homePhone;  }
  public String getMobilePhone() {    return mobilePhone;  }
  public String getWorkPhone() {    return workPhone;  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", address='" + address + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
    return address != null ? address.equals(that.address) : that.address == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    return result;
  }
}
