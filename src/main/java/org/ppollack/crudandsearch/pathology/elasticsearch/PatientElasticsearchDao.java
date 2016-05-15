package org.ppollack.crudandsearch.pathology.elasticsearch;

import org.elasticsearch.index.query.QueryBuilders;
import org.ppollack.crudandsearch.dao.IUpsertAndSearchDao;
import org.ppollack.crudandsearch.exception.CrudException;
import org.ppollack.crudandsearch.pathology.common.dao.IPersonDao;
import org.ppollack.crudandsearch.pathology.common.model.IPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

// example "get all": curl -XGET 'http://localhost:9200/patient-index/_search?pretty' -d'{ "query": { "match_all": {}} }'
@Component
public class PatientElasticsearchDao implements IPersonDao<IPerson, String>,
    IUpsertAndSearchDao<IPerson> {

  private static final Logger LOG = Logger.getLogger(PatientElasticsearchDao.class.getName());

  @Autowired PatientSearchRepository patientSearchRepository;
  @Autowired ElasticsearchTemplate elasticsearchTemplate;

  @Override
  public PatientElasticsearch getById(String s) {
    return patientSearchRepository.findOne(s);
  }

  @Override
  public Page<? extends IPerson> search(String query) {
    SearchQuery searchQuery = new NativeSearchQueryBuilder()
        .withQuery(QueryBuilders.multiMatchQuery(query, "firstName", "middleName", "lastName"))
        .build();
    Page<PatientElasticsearch> page =
        elasticsearchTemplate.queryForPage(searchQuery, PatientElasticsearch.class);
    return page;
  }

  @Override
  public void upsert(IPerson person) throws CrudException {
    PatientElasticsearch esPerson = toElasticsearchPerson(person);
    patientSearchRepository.save(esPerson);
  }

  @Override
  public void delete(IPerson person) throws CrudException {
    patientSearchRepository.delete(extractElasticsearchId(person));
  }

  private String extractElasticsearchId(IPerson person) {
    return person.getDatasourceName() + ':' + person.getId();
  }

  // convert to concrete type to facilitate json serialization
  private PatientElasticsearch toElasticsearchPerson(IPerson person) {
    PatientElasticsearch esPerson = new PatientElasticsearch();
    esPerson.setDatasourceName(person.getDatasourceName());
    esPerson.setId(extractElasticsearchId(person));
    esPerson.setFirstName(person.getFirstName());
    esPerson.setMiddleName(person.getMiddleName());
    esPerson.setLastName(person.getLastName());
    esPerson.setMailingAddresses(person.getMailingAddresses());
    esPerson.setPhoneNumbers(person.getPhoneNumbers());
    return esPerson;
  }
}
