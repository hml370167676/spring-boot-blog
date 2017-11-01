package com.hml.blog.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>Description:</p>
 *
 * @author minglu@toutoujinrong.com
 * @date 2017/11/1 14:28
 */
@Entity
@Table(name = "atp_user", schema = "atp", catalog = "")
public class AtpUserEntity extends BaseEntity implements Serializable{

  private int userId;
  private String userName;
  private String userPassword;
  private String userEmail;
  private String userPhone;

  @Id
  @Column(name = "user_id", nullable = false)
  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  @Basic
  @Column(name = "user_name", nullable = true, length = 255)
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  @Basic
  @Column(name = "user_password", nullable = true, length = 255)
  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  @Basic
  @Column(name = "user_email", nullable = true, length = 255)
  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  @Basic
  @Column(name = "user_phone", nullable = true, length = 255)
  public String getUserPhone() {
    return userPhone;
  }

  public void setUserPhone(String userPhone) {
    this.userPhone = userPhone;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    AtpUserEntity that = (AtpUserEntity) o;

    if (userId != that.userId) {
      return false;
    }
    if (userName != null ? !userName.equals(that.userName) : that.userName != null) {
      return false;
    }
    if (userPassword != null ? !userPassword.equals(that.userPassword)
        : that.userPassword != null) {
      return false;
    }
    if (userEmail != null ? !userEmail.equals(that.userEmail) : that.userEmail != null) {
      return false;
    }
    if (userPhone != null ? !userPhone.equals(that.userPhone) : that.userPhone != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = userId;
    result = 31 * result + (userName != null ? userName.hashCode() : 0);
    result = 31 * result + (userPassword != null ? userPassword.hashCode() : 0);
    result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
    result = 31 * result + (userPhone != null ? userPhone.hashCode() : 0);
    return result;
  }
}
