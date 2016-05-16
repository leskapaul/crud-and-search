package org.ppollack.crudandsearch.pathology.mongodb;

import org.ppollack.crudandsearch.pathology.common.dao.IPersonDao;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PatientMongodbDao implements IPersonDao<PatientMongodb, String> {

  private Map<String, PatientMongodb> data = new HashMap<>();

  @Override
  public PatientMongodb getById(String id) {
    return data.get(id);
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
