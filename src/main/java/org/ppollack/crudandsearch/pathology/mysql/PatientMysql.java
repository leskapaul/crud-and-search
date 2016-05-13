package org.ppollack.crudandsearch.pathology.mysql;

import org.ppollack.crudandsearch.pathology.common.dao.PatientCrudDao;
import org.ppollack.crudandsearch.pathology.common.model.IMailingAddress;
import org.ppollack.crudandsearch.pathology.common.model.IPerson;
import org.ppollack.crudandsearch.pathology.common.model.IPhoneNumber;

import java.util.List;

public class PatientMysql implements IPerson<Long> {

  private Long id;

  private String fname, mname, lname;

  public String getFname() {
    return fname;
  }

  public void setFname(String fname) {
    this.fname = fname;
  }

  public String getMname() {
    return mname;
  }

  public void setMname(String mname) {
    this.mname = mname;
  }

  public String getLname() {
    return lname;
  }

  public void setLname(String lname) {
    this.lname = lname;
  }

  @Override
  public String getDatasourceName() {
    return PatientCrudDao.PATIENT_MYSQL.name();
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public String getFirstName() {
    return fname;
  }

  @Override
  public String getMiddleName() {
    return mname;
  }

  @Override
  public String getLastName() {
    return lname;
  }

  @Override
  public List<IMailingAddress> getMailingAddresses() {
    return null;
  }

  @Override
  public List<IPhoneNumber> getPhoneNumbers() {
    return null;
  }
}
