package org.ppollack.crudandsearch.dao;

import org.ppollack.crudandsearch.exception.CrudException;

public interface IUpsertAndDeleteDao<T> {

  public void upsert(T entity) throws CrudException;

  public void delete(T entity) throws CrudException;
}
