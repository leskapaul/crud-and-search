package org.ppollack.crudandsearch.pathology.mysql;

import org.ppollack.crudandsearch.pathology.common.dao.PatientCrudDao;
import org.ppollack.crudandsearch.pathology.common.model.Person;

public class PatientMysql extends Person<Long> {

  @Override
  public String getDatasourceName() {
    return PatientCrudDao.PATIENT_MYSQL.name();
  }

  @Override
  public void setDatasourceName(String name) {
    // no-op
  }

}
