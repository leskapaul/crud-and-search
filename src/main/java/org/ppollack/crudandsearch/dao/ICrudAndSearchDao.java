package org.ppollack.crudandsearch.dao;

import org.ppollack.crudandsearch.model.IAddressableEntity;

import java.io.Serializable;

public interface ICrudAndSearchDao<T extends IAddressableEntity> extends IUpsertAndDeleteDao<T>, ISearchDao<T> {

  T get(String datasourceName, Serializable id);
}
