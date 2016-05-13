package org.ppollack.crudandsearch.pathology.mongodb;

import org.ppollack.crudandsearch.pathology.common.dao.PatientCrudDao;
import org.ppollack.crudandsearch.pathology.common.model.IMailingAddress;
import org.ppollack.crudandsearch.pathology.common.model.IPerson;
import org.ppollack.crudandsearch.pathology.common.model.IPhoneNumber;

import java.util.List;

public class PatientMongodb implements IPerson<String> {

  private String id, firstName, middleName, lastName;

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public String getDatasourceName() {
    return PatientCrudDao.PATIENT_MONGODB.name();
  }

  @Override
  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String getFirstName() {
    return firstName;
  }

  @Override
  public String getMiddleName() {
    return middleName;
  }

  @Override
  public String getLastName() {
    return lastName;
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
