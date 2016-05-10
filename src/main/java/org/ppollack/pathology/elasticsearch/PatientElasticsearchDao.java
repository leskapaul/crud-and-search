package org.ppollack.pathology.elasticsearch;

import org.ppollack.crudandsearch.IPersonDao;
import org.ppollack.crudandsearch.ISearchDao;
import org.ppollack.pathology.model.IPerson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientElasticsearchDao implements IPersonDao<IPerson>, ISearchDao<IPerson> {

  private Map<String, IPerson> data = new HashMap<>();

  @Override
  public IPerson getById(Object id) {
    String typedId = String.valueOf(id); // for elasticsearch, stringify id for simplicity
    return data.get(typedId);
  }

  @Override
  public void upsert(IPerson person) {
    String typedId = String.valueOf(person.getId());
    data.put(typedId, person);
  }

  @Override
  public void delete(IPerson person) {
    data.remove(String.valueOf(person.getId()));
  }

  @Override
  public List<IPerson> search(String query) {
    // TODO this is a mock implementation
    List<IPerson> results = new ArrayList<>();
    for (IPerson patient : data.values()) {
      if (safeStringContains(patient.getFirstName(), query)) {
        results.add(patient);
      } else if (safeStringContains(patient.getLastName(), query)) {
        results.add(patient);
      }
    }
    return results;
  }

  private boolean safeStringContains(String string, String query) {
    if (string == null || query == null) return false;

    string = string.toLowerCase();
    query = query.toLowerCase();

    String[] tokens = query.split("\\s");
    for (String token : tokens) {
      if (string.contains(token.trim())) return true;
    }
    return false;
  }
}
