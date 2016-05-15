package org.ppollack.crudandsearch.pathology.common.model;

import java.io.Serializable;
import java.util.List;

public class Person<T extends Serializable> implements IPerson<T> {

  private T id;
  private String datasourceName, firstName, middleName, lastName;
  private List<MailingAddress> mailingAddresses;
  private List<PhoneNumber> phoneNumbers;

  public void setDatasourceName(String name) {
    this.datasourceName = name;
  }

  @Override
  public String getDatasourceName() {
    return datasourceName;
  }

  @Override
  public void setId(T id) {
    this.id = id;
  }

  @Override
  public T getId() {
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
  public List<MailingAddress> getMailingAddresses() {
    return mailingAddresses;
  }

  @Override
  public List<PhoneNumber> getPhoneNumbers() {
    return phoneNumbers;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setMailingAddresses(List<MailingAddress> mailingAddresses) {
    this.mailingAddresses = mailingAddresses;
  }

  public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
    this.phoneNumbers = phoneNumbers;
  }
}
