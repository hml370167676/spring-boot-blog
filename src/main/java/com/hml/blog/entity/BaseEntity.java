package com.hml.blog.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <p>Description:</p>
 *
 * @author minglu@toutoujinrong.com
 * @date 2017/11/1 16:18
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

  @Column(name = "create_time")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createTime;
  @Column(name = "update_time")
  @Temporal(TemporalType.TIMESTAMP)
  private Date updateTime;

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  @PrePersist
  public void prePersist() {
    setCreateTime(new Date());
    setUpdateTime(new Date());
  }

  @PreUpdate
  public void preUpdate() {
    setUpdateTime(new Date());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
    result = prime * result + ((updateTime == null) ? 0 : updateTime.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    BaseEntity other = (BaseEntity) obj;
    if (createTime == null) {
      if (other.createTime != null) {
        return false;
      }
    } else if (!createTime.equals(other.createTime)) {
      return false;
    }
    if (updateTime == null) {
      if (other.updateTime != null) {
        return false;
      }
    } else if (!updateTime.equals(other.updateTime)) {
      return false;
    }
    return true;
  }


}