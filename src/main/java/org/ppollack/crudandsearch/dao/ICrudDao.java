package org.ppollack.crudandsearch.dao;

import java.io.Serializable;

public interface ICrudDao<T, ID extends Serializable> extends IUpsertAndDeleteDao<T> {

  public T getById(ID id);

}
