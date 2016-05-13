package org.ppollack.crudandsearch.pathology.common.dao;

import org.ppollack.crudandsearch.pathology.mongodb.PatientMongodbDao;
import org.ppollack.crudandsearch.pathology.mysql.PatientMysqlDao;

public enum PatientCrudDao {
  PATIENT_MYSQL(new PatientMysqlDao()),
  PATIENT_MONGODB(new PatientMongodbDao());

  private IPersonDao dao;

  PatientCrudDao(IPersonDao dao) {
    this.dao = dao;
  }

  IPersonDao getDao() {
    return this.dao;
  }
}
