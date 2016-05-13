package org.ppollack.crudandsearch.dao;

import org.ppollack.crudandsearch.pathology.common.model.IPerson;

public interface IPersonDao<T extends IPerson> {

  public T getById(Object id);

  public void upsert(T person);

  public void delete(T person);
}
