package org.ppollack.crudandsearch.dao;

import org.ppollack.crudandsearch.exception.CrudException;
import org.ppollack.crudandsearch.model.IAddressableEntity;

import java.util.List;

public class BasicCrudAndSearchDao<T extends IAddressableEntity> implements ICrudAndSearchDao<T> {

  private ICrudDaoResolver crudDaoResolver;
  private IUpsertAndSearchDao<T> upsertAndSearchDao;

  public BasicCrudAndSearchDao(ICrudDaoResolver crudDaoResolver,
      IUpsertAndSearchDao<T> upsertAndSearchDao) {
    this.crudDaoResolver = crudDaoResolver;
    this.upsertAndSearchDao = upsertAndSearchDao;
  }

  @Override
  public T get(String datasourceName, Object id) {
    return (T) crudDaoResolver.resolveDao(datasourceName).getById(id);
  }

  @Override
  public void upsert(T entity) throws CrudException {
    ICrudDao<T, ?> crudDao = crudDaoResolver.resolveDao(entity.getDatasourceName());
    crudDao.upsert(entity);
    upsertAndSearchDao.upsert(entity);
  }

  @Override
  public void delete(T entity) throws CrudException {
    ICrudDao<T, ?> crudDao = crudDaoResolver.resolveDao(entity.getDatasourceName());
    crudDao.delete(entity);
    upsertAndSearchDao.upsert(entity);
  }

  @Override
  public List<T> search(String query) {
    return upsertAndSearchDao.search(query);
  }
}
