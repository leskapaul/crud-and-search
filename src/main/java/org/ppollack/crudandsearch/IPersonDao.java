package org.ppollack.crudandsearch;

import org.ppollack.pathology.model.IPerson;

public interface IPersonDao<T extends IPerson> {

  public T getById(Object id);

  public void upsert(T person);

  public void delete(T person);
}
