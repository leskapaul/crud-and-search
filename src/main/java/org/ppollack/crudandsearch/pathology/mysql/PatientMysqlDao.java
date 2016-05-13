package org.ppollack.crudandsearch.pathology.mysql;

import org.ppollack.crudandsearch.pathology.common.dao.IPersonDao;
import org.ppollack.crudandsearch.IdTypeResolver;

import java.util.HashMap;
import java.util.Map;

public class PatientMysqlDao implements IPersonDao<PatientMysql> {

  private static final Class<Long> ID_TYPE = Long.class;

  private Map<Long, PatientMysql> data = new HashMap<>();

  @Override
  public PatientMysql getById(Object id) {
    Long typedId = IdTypeResolver.typifyId(ID_TYPE, id);
    // TODO
    return data.get(typedId);
  }

  @Override
  public void upsert(PatientMysql person) {
    // TODO
    data.put(person.getId(), person);
  }

  @Override
  public void delete(PatientMysql person) {
    data.remove(person.getId());
  }


}
