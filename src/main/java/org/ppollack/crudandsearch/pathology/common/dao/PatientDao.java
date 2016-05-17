package org.ppollack.crudandsearch.pathology.common.dao;

import org.ppollack.crudandsearch.dao.BasicCrudAndSearchDao;
import org.ppollack.crudandsearch.dao.ICrudDao;
import org.ppollack.crudandsearch.pathology.common.model.IPerson;
import org.ppollack.crudandsearch.pathology.elasticsearch.PatientElasticsearchDao;
import org.ppollack.crudandsearch.pathology.mongodb.PatientMongodbDao;
import org.ppollack.crudandsearch.pathology.mysql.PatientMysqlDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientDao extends BasicCrudAndSearchDao<IPerson> {

  private PatientMysqlDao patientMysqlDao;
  private PatientMongodbDao patientMongodbDao;

  @Autowired
  public PatientDao(PatientElasticsearchDao searchDao, PatientMysqlDao patientMysqlDao,
      PatientMongodbDao patientMongodbDao) {
    super(searchDao);
    this.patientMysqlDao = patientMysqlDao;
    this.patientMongodbDao = patientMongodbDao;
  }

  @Override
  public ICrudDao resolveDao(String name) {
    switch (name) {
      case "PATIENT_MONGODB":
        return patientMongodbDao;
      case "PATIENT_MYSQL":
        return patientMysqlDao;
      default:
        throw new IllegalArgumentException("no data access object known by name " + name);
    }
  }
}
