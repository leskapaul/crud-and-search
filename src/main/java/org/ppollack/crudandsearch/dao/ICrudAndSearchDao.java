package org.ppollack.crudandsearch.dao;

public interface ICrudAndSearchDao<T, E> extends IUpsertAndDeleteDao<T>, ISearchDao<T> {

  T get(E crudDaoEnum, Object id);
}
