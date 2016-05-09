package org.ppollack.pathology.model;

public interface IMailingAddress {

  MailingAddressType getType();
  String getAddressLine1();
  String getAddressLine2();
  String getCity();
  String getState();
  String getPostalCode();
}
