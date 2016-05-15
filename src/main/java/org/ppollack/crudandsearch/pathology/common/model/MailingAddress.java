package org.ppollack.crudandsearch.pathology.common.model;

public class MailingAddress implements IMailingAddress {

  private MailingAddressType type;
  private String line1, line2, city, state, postalCode;

  @Override
  public MailingAddressType getType() {
    return type;
  }

  @Override
  public String getAddressLine1() {
    return line1;
  }

  @Override
  public String getAddressLine2() {
    return line2;
  }

  @Override
  public String getCity() {
    return city;
  }

  @Override
  public String getState() {
    return state;
  }

  @Override
  public String getPostalCode() {
    return postalCode;
  }

  public void setType(MailingAddressType type) {
    this.type = type;
  }

  public void setLine1(String line1) {
    this.line1 = line1;
  }

  public void setLine2(String line2) {
    this.line2 = line2;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setState(String state) {
    this.state = state;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }
}
