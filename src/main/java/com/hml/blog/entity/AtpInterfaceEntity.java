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
@Table(name = "atp_interface", schema = "atp", catalog = "")
public class AtpInterfaceEntity extends BaseEntity implements Serializable {

  private int interfaceId;
  private String interfaceName;

  @Id
  @Column(name = "interface_id", nullable = false)
  public int getInterfaceId() {
    return interfaceId;
  }

  public void setInterfaceId(int interfaceId) {
    this.interfaceId = interfaceId;
  }

  @Basic
  @Column(name = "interface_name", nullable = true, length = 255)
  public String getInterfaceName() {
    return interfaceName;
  }

  public void setInterfaceName(String interfaceName) {
    this.interfaceName = interfaceName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    AtpInterfaceEntity that = (AtpInterfaceEntity) o;

    if (interfaceId != that.interfaceId) {
      return false;
    }
    if (interfaceName != null ? !interfaceName.equals(that.interfaceName)
        : that.interfaceName != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = interfaceId;
    result = 31 * result + (interfaceName != null ? interfaceName.hashCode() : 0);
    return result;
  }
}
