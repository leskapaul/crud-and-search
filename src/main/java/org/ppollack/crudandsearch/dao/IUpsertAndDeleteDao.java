package org.ppollack.crudandsearch.dao;

import org.ppollack.crudandsearch.exception.CrudException;

public interface IUpsertAndDeleteDao<T> {

  public void upsert(T person) throws CrudException;

  public void delete(T person) throws CrudException;
}
