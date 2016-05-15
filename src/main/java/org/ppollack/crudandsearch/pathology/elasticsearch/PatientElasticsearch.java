package org.ppollack.crudandsearch.pathology.elasticsearch;

import org.ppollack.crudandsearch.pathology.common.model.Person;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "patient-index", shards = 1, replicas = 0)
public class PatientElasticsearch extends Person<String> {

  @Override
  @Id
  public void setId(String id) {
    super.setId(id);
  }

  @Override
  @Id
  public String getId() {
    return super.getId();
  }

}
