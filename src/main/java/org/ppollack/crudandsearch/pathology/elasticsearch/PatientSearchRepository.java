package org.ppollack.crudandsearch.pathology.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

public interface PatientSearchRepository extends ElasticsearchCrudRepository<PatientElasticsearch, String> {
}
