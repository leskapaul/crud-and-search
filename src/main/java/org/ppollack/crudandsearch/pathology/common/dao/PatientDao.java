package org.ppollack.crudandsearch.pathology.common.dao;

import org.ppollack.crudandsearch.dao.BasicCrudAndSearchDao;
import org.ppollack.crudandsearch.dao.ICrudDaoResolver;
import org.ppollack.crudandsearch.pathology.common.model.IPerson;
import org.ppollack.crudandsearch.pathology.elasticsearch.PatientElasticsearchDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientDao extends BasicCrudAndSearchDao<IPerson> {

  @Autowired
  public PatientDao(PatientElasticsearchDao searchDao) {
    super(buildCrudDaoResolver(), searchDao);
  }

  private static ICrudDaoResolver buildCrudDaoResolver() {
    return name -> PatientCrudDao.valueOf(name).getDao();
  }
}
