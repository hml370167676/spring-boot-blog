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
@Table(name = "atp_interface_type", schema = "atp", catalog = "")
public class AtpInterfaceTypeEntity extends BaseEntity implements Serializable{

  private int typeId;
  private String typeName;

  @Id
  @Column(name = "type_id", nullable = false)
  public int getTypeId() {
    return typeId;
  }

  public void setTypeId(int typeId) {
    this.typeId = typeId;
  }

  @Basic
  @Column(name = "type_name", nullable = true, length = 255)
  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    AtpInterfaceTypeEntity that = (AtpInterfaceTypeEntity) o;

    if (typeId != that.typeId) {
      return false;
    }
    if (typeName != null ? !typeName.equals(that.typeName) : that.typeName != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = typeId;
    result = 31 * result + (typeName != null ? typeName.hashCode() : 0);
    return result;
  }
}
