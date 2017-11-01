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
@Table(name = "atp_system", schema = "atp", catalog = "")
public class AtpSystemEntity implements Serializable {

  private int systemId;
  private String systemName;

  @Id
  @Column(name = "system_id", nullable = false)
  public int getSystemId() {
    return systemId;
  }

  public void setSystemId(int systemId) {
    this.systemId = systemId;
  }

  @Basic
  @Column(name = "system_name", nullable = true, length = 255)
  public String getSystemName() {
    return systemName;
  }

  public void setSystemName(String systemName) {
    this.systemName = systemName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    AtpSystemEntity that = (AtpSystemEntity) o;

    if (systemId != that.systemId) {
      return false;
    }
    if (systemName != null ? !systemName.equals(that.systemName) : that.systemName != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = systemId;
    result = 31 * result + (systemName != null ? systemName.hashCode() : 0);

    return result;
  }
}
