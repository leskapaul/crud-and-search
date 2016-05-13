package org.ppollack.crudandsearch.pathology.common.dao;

import org.ppollack.crudandsearch.dao.BasicCrudAndSearchDao;
import org.ppollack.crudandsearch.dao.ICrudDaoResolver;
import org.ppollack.crudandsearch.pathology.common.model.IPerson;
import org.ppollack.crudandsearch.pathology.elasticsearch.PatientElasticsearchDao;

public class PatientDao extends BasicCrudAndSearchDao<IPerson> {

  public PatientDao() {
    super(buildCrudDaoResolver(), new PatientElasticsearchDao());
  }

  private static ICrudDaoResolver buildCrudDaoResolver() {
    return name -> PatientCrudDao.valueOf(name).getDao();
  }
}
