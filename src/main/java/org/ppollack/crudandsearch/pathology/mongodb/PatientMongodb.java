package org.ppollack.crudandsearch.pathology.mongodb;

import org.ppollack.crudandsearch.pathology.common.dao.PatientCrudDao;
import org.ppollack.crudandsearch.pathology.common.model.IMailingAddress;
import org.ppollack.crudandsearch.pathology.common.model.IPerson;
import org.ppollack.crudandsearch.pathology.common.model.IPhoneNumber;
import org.ppollack.crudandsearch.pathology.common.model.Person;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection="patient")
public class PatientMongodb extends Person<String> {

  @Override
  public String getDatasourceName() {
    return PatientCrudDao.PATIENT_MONGODB.name();
  }

  @Override
  public void setDatasourceName(String name) {
    // no-op
  }

  @Override
  @Id
  @Field("userId")
  public void setId(String id) {
    super.setId(id);
  }

  @Override
  @Id
  @Field("userId")
  public String getId() {
    return super.getId();
  }
}
