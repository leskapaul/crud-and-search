package org.ppollack.crudandsearch.pathology.mysql;

import org.ppollack.crudandsearch.pathology.common.dao.IPersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientMysqlDao implements IPersonDao<PatientMysql, Long> {

  @Autowired private PatientMysqlRepository patientMysqlRepository;

  @Override
  public PatientMysql getById(Long id) {
    return patientMysqlRepository.findOne(id);
  }

  @Override
  public void upsert(PatientMysql person) {
    patientMysqlRepository.save(person);
  }

  @Override
  public void delete(PatientMysql person) {
    patientMysqlRepository.delete(person.getId());
  }


}
