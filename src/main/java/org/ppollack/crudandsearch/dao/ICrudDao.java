package org.ppollack.crudandsearch.dao;

public interface ICrudDao<T, K> extends IUpsertAndDeleteDao<T> {

  public T getById(K id);

}
