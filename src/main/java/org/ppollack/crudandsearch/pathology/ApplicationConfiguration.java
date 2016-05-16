package org.ppollack.crudandsearch.pathology;

import org.elasticsearch.client.Client;
import org.ppollack.crudandsearch.pathology.elasticsearch.PatientElasticsearch;
import org.ppollack.crudandsearch.pathology.elasticsearch.PatientSearchRepository;
import org.ppollack.crudandsearch.pathology.mysql.PatientMysqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.TransportClientFactoryBean;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
@EnableElasticsearchRepositories
@EnableJpaRepositories
public class ApplicationConfiguration {

  @Autowired ElasticsearchOperations operations;
  @Autowired PatientSearchRepository patientSearchRepository;
  @Autowired PatientMysqlRepository patientMysqlRepository;

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
    patientMysqlRepository.deleteAll();
    patientSearchRepository.deleteAll();
    refreshPatientIndex();
  }

  public void refreshPatientIndex() {
    operations.refresh(PatientElasticsearch.class);
  }

}
