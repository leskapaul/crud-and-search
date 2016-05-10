package org.ppollack.crudandsearch;

import org.ppollack.pathology.mongodb.PatientMongodbDao;
import org.ppollack.pathology.mysql.PatientMysqlDao;

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
