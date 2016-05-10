package org.ppollack.crudandsearch;

import org.ppollack.pathology.elasticsearch.PatientElasticsearchDao;
import org.ppollack.pathology.model.IPerson;

import java.util.List;

public class PatientDao {

  private PatientElasticsearchDao searchEngineDao = new PatientElasticsearchDao();

  public IPerson get(PatientCrudDao crudDao, Object id) {
    return crudDao.getDao().getById(id);
  }

  public void upsert(IPerson patient) {
    PatientCrudDao crudDao = PatientCrudDao.valueOf(patient.getDatasourceName());

    // upsert the data in its data store
    crudDao.getDao().upsert(patient);

    // upsert the data in the search engine index
    searchEngineDao.upsert(patient);
  }

  public void delete(IPerson patient) {
    PatientCrudDao crudDao = PatientCrudDao.valueOf(patient.getDatasourceName());
    crudDao.getDao().delete(patient);
  }

  public List<IPerson> search(String query) {
    return searchEngineDao.search(query);
  }
}
