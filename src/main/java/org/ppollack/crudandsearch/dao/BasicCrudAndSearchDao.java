package org.ppollack.crudandsearch.dao;

import org.ppollack.crudandsearch.exception.CrudException;
import org.ppollack.crudandsearch.model.IAddressableEntity;
import org.springframework.data.domain.Page;

import java.io.Serializable;

/**
 * This class is intended to facilitate CRUD and search operations against disparate
 * data stores.  Writes via this class will be propagated to a search engine via the encapsulated
 * search DAO, to facilitate platform independent search calls.
 *
 * @param <T> the type of object that this data access object shall manage
 */
public abstract class BasicCrudAndSearchDao<T extends IAddressableEntity>
    implements ICrudAndSearchDao<T>, ICrudDaoResolver {

  private IUpsertAndSearchDao<T> upsertAndSearchDao;

  public BasicCrudAndSearchDao(IUpsertAndSearchDao<T> upsertAndSearchDao) {
    this.upsertAndSearchDao = upsertAndSearchDao;
  }

  @Override
  public T get(String datasourceName, Serializable id) {
    return (T) resolveDao(datasourceName).getById(id);
  }

  @Override
  public void upsert(T entity) throws CrudException {
    ICrudDao<T, ?> crudDao = resolveDao(entity.getDatasourceName());
    crudDao.upsert(entity);
    upsertAndSearchDao.upsert(entity);
  }

  @Override
  public void delete(T entity) throws CrudException {
    ICrudDao<T, ?> crudDao = resolveDao(entity.getDatasourceName());
    crudDao.delete(entity);
    upsertAndSearchDao.upsert(entity);
  }

  @Override
  public Page<? extends T> search(String query) {
    return upsertAndSearchDao.search(query);
  }
}
