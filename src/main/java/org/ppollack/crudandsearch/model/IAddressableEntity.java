package org.ppollack.crudandsearch.model;

public interface IAddressableEntity<T> {

  String getDatasourceName();

  void setId(T id);
  T getId();

}
