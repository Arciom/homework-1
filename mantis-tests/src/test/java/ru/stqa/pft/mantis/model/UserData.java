package ru.stqa.pft.mantis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mantis_user_table") //название таблицы

public class UserData {

  @Column(name = "username") //название столбца в таблице
  private String username;

  @Id
  @Column(name = "id") //название столбца в таблице
  private int id;

  @Column(name = "email") //название столбца в таблице
  private String email;

  @Column(name = "password") //название столбца в таблице
  private String password;

  public String getUsername() {    return username;  }
  public int getId() {    return id;  }
  public String getEmail() {    return email;  }
  public String getPassword() {    return password;  }

  public void setUsername(String username) {    this.username = username;  }
  public void setId(int id) {    this.id = id;  }
  public void setEmail(String email) {    this.email = email;  }
  public void setPassword(String password) {    this.password = password;  }

  @Override
  public String toString() {
    return "UserData{" +
            "username='" + username + '\'' +
            ", id=" + id +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            '}';
  }
}
