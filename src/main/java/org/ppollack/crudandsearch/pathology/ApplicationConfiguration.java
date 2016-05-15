package org.ppollack.crudandsearch.pathology;

import org.elasticsearch.client.Client;
import org.ppollack.crudandsearch.pathology.common.model.MailingAddress;
import org.ppollack.crudandsearch.pathology.common.model.MailingAddressType;
import org.ppollack.crudandsearch.pathology.elasticsearch.PatientElasticsearch;
import org.ppollack.crudandsearch.pathology.elasticsearch.PatientSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.TransportClientFactoryBean;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
@EnableElasticsearchRepositories
public class ApplicationConfiguration {

  @Autowired ElasticsearchOperations operations;
  @Autowired PatientSearchRepository patientSearchRepository;

  @Bean
  public TransportClientFactoryBean client() {
    TransportClientFactoryBean bean = new TransportClientFactoryBean();
    bean.setClientTransportSniff(false);
    bean.setClusterNodes("localhost:9300");
    return bean;
  }

  @Bean
  public ElasticsearchTemplate elasticsearchTemplate(Client client) throws Exception {
    return new ElasticsearchTemplate(client);
  }

  @PreDestroy
  public void preDestroy() {
    //operations.deleteIndex(PatientElasticsearch.class);
  }

  @PostConstruct
  public void postConstruct() {

    patientSearchRepository.deleteAll();
    refreshPatientIndex();

    //resetPatientIndexWithExampleData();
  }

  public void refreshPatientIndex() {
    operations.refresh(PatientElasticsearch.class);
  }

  private void resetPatientIndexWithExampleData() {
    PatientElasticsearch patient = new PatientElasticsearch();
    patient.setId("foo");
    patient.setFirstName("Paul");
    patient.setMiddleName("Michael");
    patient.setLastName("Pollack");
    patient.setDatasourceName("redis");

    patient.setMailingAddresses(new ArrayList<>());
    MailingAddress address = new MailingAddress();
    patient.getMailingAddresses().add(address);
    address.setCity("New York City");
    address.setLine1("1 World Trade Center");
    address.setLine2("45th Floor");
    address.setState("NY");
    address.setType(MailingAddressType.WORK);

    patientSearchRepository.save(patient);
  }
}
