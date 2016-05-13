package org.ppollack.crudandsearch.pathology.common.dao;

import org.ppollack.crudandsearch.dao.ICrudAndSearchDao;
import org.ppollack.crudandsearch.exception.CrudException;
import org.ppollack.crudandsearch.pathology.common.model.IPerson;
import org.ppollack.crudandsearch.pathology.elasticsearch.PatientElasticsearchDao;

import java.util.List;

public class PatientDao implements ICrudAndSearchDao<IPerson, PatientCrudDao> {

  private PatientElasticsearchDao searchEngineDao = new PatientElasticsearchDao();

  public IPerson get(PatientCrudDao crudDao, Object id) {
    return (IPerson) crudDao.getDao().getById(id);
  }

  public void upsert(IPerson patient) throws CrudException {
    PatientCrudDao crudDao = PatientCrudDao.valueOf(patient.getDatasourceName());

    // upsert the data in its data store
    crudDao.getDao().upsert(patient);

    // upsert the data in the search engine index
    searchEngineDao.upsert(patient);
  }

  public void delete(IPerson patient) throws CrudException {
    PatientCrudDao crudDao = PatientCrudDao.valueOf(patient.getDatasourceName());
    crudDao.getDao().delete(patient);
  }

  public List<IPerson> search(String query) {
    return searchEngineDao.search(query);
  }
}
