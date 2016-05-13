package org.ppollack.crudandsearch.dao;

public interface ICrudDao<T> extends IUpsertAndDeleteDao<T> {

  public T getById(Object id);

}
