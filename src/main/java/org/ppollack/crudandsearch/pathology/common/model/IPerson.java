package org.ppollack.crudandsearch.pathology.common.model;

import org.ppollack.crudandsearch.model.IAddressableEntity;

import java.io.Serializable;
import java.util.List;

public interface IPerson<T extends Serializable> extends IAddressableEntity<T> {

  String getFirstName();
  String getMiddleName();
  String getLastName();

  List<? extends IMailingAddress> getMailingAddresses();

  List<? extends IPhoneNumber> getPhoneNumbers();

}
