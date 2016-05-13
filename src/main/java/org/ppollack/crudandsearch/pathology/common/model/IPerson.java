package org.ppollack.crudandsearch.pathology.common.model;

import java.util.List;

public interface IPerson<T> {

  String getDatasourceName();

  void setId(T id);
  T getId();

  String getFirstName();
  String getMiddleName();
  String getLastName();

  List<IMailingAddress> getMailingAddresses();

  List<IPhoneNumber> getPhoneNumbers();

}
