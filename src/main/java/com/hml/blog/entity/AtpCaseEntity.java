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
@Table(name = "atp_case", schema = "atp", catalog = "")
public class AtpCaseEntity extends BaseEntity implements Serializable{

  private int caseId;
  private String caseRequest;
  private String caseResponse;
  private String caseExpect;
  private Integer userId;

  @Id
  @Column(name = "case_id", nullable = false)
  public int getCaseId() {
    return caseId;
  }

  public void setCaseId(int caseId) {
    this.caseId = caseId;
  }

  @Basic
  @Column(name = "case_request", nullable = true, length = 255)
  public String getCaseRequest() {
    return caseRequest;
  }

  public void setCaseRequest(String caseRequest) {
    this.caseRequest = caseRequest;
  }

  @Basic
  @Column(name = "case_response", nullable = true, length = 255)
  public String getCaseResponse() {
    return caseResponse;
  }

  public void setCaseResponse(String caseResponse) {
    this.caseResponse = caseResponse;
  }

  @Basic
  @Column(name = "case_expect", nullable = true, length = 255)
  public String getCaseExpect() {
    return caseExpect;
  }

  public void setCaseExpect(String caseExpect) {
    this.caseExpect = caseExpect;
  }

  @Basic
  @Column(name = "user_id", nullable = true)
  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    AtpCaseEntity that = (AtpCaseEntity) o;

    if (caseId != that.caseId) {
      return false;
    }
    if (caseRequest != null ? !caseRequest.equals(that.caseRequest) : that.caseRequest != null) {
      return false;
    }
    if (caseResponse != null ? !caseResponse.equals(that.caseResponse)
        : that.caseResponse != null) {
      return false;
    }
    if (caseExpect != null ? !caseExpect.equals(that.caseExpect) : that.caseExpect != null) {
      return false;
    }
    if (userId != null ? !userId.equals(that.userId) : that.userId != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = caseId;
    result = 31 * result + (caseRequest != null ? caseRequest.hashCode() : 0);
    result = 31 * result + (caseResponse != null ? caseResponse.hashCode() : 0);
    result = 31 * result + (caseExpect != null ? caseExpect.hashCode() : 0);
    result = 31 * result + (userId != null ? userId.hashCode() : 0);
    return result;
  }
}
