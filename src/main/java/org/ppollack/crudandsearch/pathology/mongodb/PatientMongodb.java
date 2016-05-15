package org.ppollack.crudandsearch.pathology.mongodb;

import org.ppollack.crudandsearch.pathology.common.dao.PatientCrudDao;
import org.ppollack.crudandsearch.pathology.common.model.IMailingAddress;
import org.ppollack.crudandsearch.pathology.common.model.IPerson;
import org.ppollack.crudandsearch.pathology.common.model.IPhoneNumber;
import org.ppollack.crudandsearch.pathology.common.model.Person;

import java.util.List;

public class PatientMongodb extends Person<String> {

  @Override
  public String getDatasourceName() {
    return PatientCrudDao.PATIENT_MONGODB.name();
  }

  @Override
  public void setDatasourceName(String name) {
    // no-op
  }
}
