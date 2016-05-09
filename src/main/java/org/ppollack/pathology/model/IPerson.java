package org.ppollack.pathology.model;

import org.ppollack.crudandsearch.model.Identifiable;

import java.util.List;

public interface IPerson<I> extends Identifiable<I> {

  String getFirstName();
  String getMiddleName();
  String getLastName();

  List<IMailingAddress> getMailingAddresses();

  List<IPhoneNumber> getPhoneNumbers();

}
