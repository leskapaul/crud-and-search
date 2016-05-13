package org.ppollack.crudandsearch.pathology.mysql;

import org.ppollack.crudandsearch.pathology.common.dao.IPersonDao;

import java.util.HashMap;
import java.util.Map;

public class PatientMysqlDao implements IPersonDao<PatientMysql, Long> {

  private Map<Long, PatientMysql> data = new HashMap<>();

  @Override
  public PatientMysql getById(Long id) {
    return data.get(id);
  }

  @Override
  public void upsert(PatientMysql person) {
    data.put(person.getId(), person);
  }

  @Override
  public void delete(PatientMysql person) {
    data.remove(person.getId());
  }


}
