package org.ppollack.crudandsearch.pathology.mongodb;

import org.ppollack.crudandsearch.dao.IPersonDao;
import org.ppollack.crudandsearch.IdTypeResolver;

import java.util.HashMap;
import java.util.Map;

public class PatientMongodbDao implements IPersonDao<PatientMongodb> {

  private static final Class<String> ID_TYPE = String.class;

  private Map<String, PatientMongodb> data = new HashMap<>();

  @Override
  public PatientMongodb getById(Object id) {
    String typedId = IdTypeResolver.typifyId(ID_TYPE, id);
    // TODO
    return data.get(typedId);
  }

  @Override
  public void upsert(PatientMongodb person) {
    data.put(person.getId(), person);
  }

  @Override
  public void delete(PatientMongodb person) {
    data.remove(person.getId());
  }
}
