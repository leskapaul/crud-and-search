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

  @Autowired PatientMysqlDao patientMysqlDao;
  @Autowired PatientMongodbDao patientMongodbDao;

  @Autowired
  public PatientDao(PatientElasticsearchDao searchDao) {
    super(searchDao);
  }

  @Override
  public ICrudDao resolveDao(String name) {
    switch (name) {
      case "PATIENT_MONGODB":
        return patientMongodbDao;
      case "PATIENT_MYSQL":
        return patientMysqlDao;
      default:
        throw new IllegalArgumentException("no DAO known by name " + name);
    }
  }
}
