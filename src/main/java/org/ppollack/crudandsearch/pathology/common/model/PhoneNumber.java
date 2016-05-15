package org.ppollack.crudandsearch.pathology.common.model;

public class PhoneNumber implements IPhoneNumber {

  private PhoneType type;
  private String number;

  @Override
  public PhoneType getType() {
    return type;
  }

  @Override
  public String getNumber() {
    return number;
  }

  public void setType(PhoneType type) {
    this.type = type;
  }

  public void setNumber(String number) {
    this.number = number;
  }
}
