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
@Table(name = "atp_role", schema = "atp", catalog = "")
public class AtpRoleEntity extends BaseEntity implements Serializable{

  private int roleId;
  private String roleName;

  @Id
  @Column(name = "role_id", nullable = false)
  public int getRoleId() {
    return roleId;
  }

  public void setRoleId(int roleId) {
    this.roleId = roleId;
  }

  @Basic
  @Column(name = "role_name", nullable = true, length = 255)
  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    AtpRoleEntity that = (AtpRoleEntity) o;

    if (roleId != that.roleId) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = roleId;
    result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
    return result;
  }
}
