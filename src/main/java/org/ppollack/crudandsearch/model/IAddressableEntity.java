package org.ppollack.crudandsearch.model;

import java.io.Serializable;

public interface IAddressableEntity<ID extends Serializable> {

  String getDatasourceName();

  void setId(ID id);
  ID getId();

}
