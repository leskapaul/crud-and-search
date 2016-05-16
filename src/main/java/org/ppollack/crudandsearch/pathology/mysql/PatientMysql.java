package org.ppollack.crudandsearch.pathology.mysql;

import org.ppollack.crudandsearch.pathology.common.dao.PatientCrudDao;
import org.ppollack.crudandsearch.pathology.common.model.MailingAddress;
import org.ppollack.crudandsearch.pathology.common.model.Person;
import org.ppollack.crudandsearch.pathology.common.model.PhoneNumber;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Patient")
public class PatientMysql extends Person<Long> {

  @Transient
  @Override
  public String getDatasourceName() {
    return PatientCrudDao.PATIENT_MYSQL.name();
  }

  @Transient
  @Override
  public void setDatasourceName(String name) {
    // no-op
  }

  @Id
  @Override
  public void setId(Long id) {
    super.setId(id);
  }

  @Id
  @Override
  public Long getId() {
    return super.getId();
  }

  @Column
  @Override
  public String getFirstName() {
    return super.getFirstName();
  }

  @Column
  @Override
  public String getMiddleName() {
    return super.getMiddleName();
  }

  @Column
  @Override
  public String getLastName() {
    return super.getLastName();
  }

  @Override
  public void setFirstName(String firstName) {
    super.setFirstName(firstName);
  }

  @Column
  @Override
  public void setMiddleName(String middleName) {
    super.setMiddleName(middleName);
  }

  @Column
  @Override
  public void setLastName(String lastName) {
    super.setLastName(lastName);
  }

  @Transient
  @Override
  public List<MailingAddress> getMailingAddresses() {
    return super.getMailingAddresses();
  }

  @Transient
  @Override
  public List<PhoneNumber> getPhoneNumbers() {
    return super.getPhoneNumbers();
  }

  @Transient
  @Override
  public void setMailingAddresses(List<MailingAddress> mailingAddresses) {
    super.setMailingAddresses(mailingAddresses);
  }

  @Transient
  @Override
  public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
    super.setPhoneNumbers(phoneNumbers);
  }
}
