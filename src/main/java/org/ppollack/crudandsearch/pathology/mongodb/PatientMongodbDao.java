package org.ppollack.crudandsearch.pathology.mongodb;

import org.ppollack.crudandsearch.pathology.common.dao.IPersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientMongodbDao implements IPersonDao<PatientMongodb, String> {

  @Autowired PatientMongodbRepository patientMongodbRepository;

  @Override
  public PatientMongodb getById(String id) {
    return patientMongodbRepository.findOne(id);
  }

  @Override
  public void upsert(PatientMongodb person) {
    patientMongodbRepository.save(person);
  }

  @Override
  public void delete(PatientMongodb person) {
    patientMongodbRepository.delete(person);
  }
}
