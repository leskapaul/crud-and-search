package org.ppollack.crudandsearch.pathology;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import org.elasticsearch.client.Client;
import org.ppollack.crudandsearch.pathology.elasticsearch.PatientElasticsearch;
import org.ppollack.crudandsearch.pathology.elasticsearch.PatientSearchRepository;
import org.ppollack.crudandsearch.pathology.mongodb.PatientMongodbRepository;
import org.ppollack.crudandsearch.pathology.mysql.PatientMysqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.TransportClientFactoryBean;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
@EnableElasticsearchRepositories("org.ppollack.crudandsearch.pathology.elasticsearch")
@EnableJpaRepositories("org.ppollack.crudandsearch.pathology.mysql")
@EnableMongoRepositories("org.ppollack.crudandsearch.pathology.mongodb")
public class ApplicationConfiguration {

  private static final Logger LOG = Logger.getLogger(ApplicationConfiguration.class.getName());

  @Autowired ElasticsearchOperations elasticsearchOperations;
  @Autowired MongoOperations mongoOperations;

  @Autowired PatientSearchRepository patientSearchRepository;
  @Autowired PatientMysqlRepository patientMysqlRepository;
  @Autowired PatientMongodbRepository patientMongodbRepository;

  @Bean
  public TransportClientFactoryBean elasticsearchClient() {
    TransportClientFactoryBean bean = new TransportClientFactoryBean();
    bean.setClientTransportSniff(false);
    bean.setClusterNodes("localhost:9300");
    return bean;
  }

  @Bean
  public ElasticsearchTemplate elasticsearchTemplate(Client client) throws Exception {
    return new ElasticsearchTemplate(client);
  }

  @Bean
  public Mongo mongo() throws Exception {
    MongoClientOptions o = new MongoClientOptions.Builder()
        .build();
    return new MongoClient("localhost:27017", o);
  }

  @Bean
  public MongoDbFactory mongoDbFactory() throws Exception {
    MongoClient mongo = (MongoClient) mongo();
    return new SimpleMongoDbFactory(mongo, "agillaire");
  }

  @Bean
  public MongoTemplate mongoTemplate() throws Exception {
    MappingMongoConverter converter =
        new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory()),
            new MongoMappingContext());
    converter.setTypeMapper(new DefaultMongoTypeMapper(null));
    return new MongoTemplate(mongoDbFactory(), converter);
  }

  @PreDestroy
  public void preDestroy() {
    //elasticsearchOperations.deleteIndex(PatientElasticsearch.class);
  }

  @PostConstruct
  public void postConstruct() {
    if (!mongoOperations.collectionExists("patient")) {
      mongoOperations.createCollection("patient");
    } else {
      LOG.info("patient collection already exists in mongodb");
    }

    patientMongodbRepository.deleteAll();
    patientMysqlRepository.deleteAll();
    patientSearchRepository.deleteAll();
    refreshPatientIndex();
    LOG.info("PostConstruct: deleted all records in all data stores");
  }

  public void refreshPatientIndex() {
    elasticsearchOperations.refresh(PatientElasticsearch.class);
  }

}
