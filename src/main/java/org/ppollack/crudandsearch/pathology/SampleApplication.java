package org.ppollack.crudandsearch.pathology;

import org.ppollack.crudandsearch.exception.CrudException;
import org.ppollack.crudandsearch.pathology.common.dao.PatientDao;
import org.ppollack.crudandsearch.pathology.common.model.IPerson;
import org.ppollack.crudandsearch.pathology.common.model.MailingAddress;
import org.ppollack.crudandsearch.pathology.common.model.MailingAddressType;
import org.ppollack.crudandsearch.pathology.mongodb.PatientMongodb;
import org.ppollack.crudandsearch.pathology.mysql.PatientMysql;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * This sample application depends on Elasticsearch running on localhost.  Use Docker Compose file
 * compose-elasticsearch.yml, in this project, to run Elasticsearch locally.
 */
public class SampleApplication {

  private static final Logger LOG = Logger.getLogger(SampleApplication.class.getName());

  public static void main(String[] args) throws CrudException {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.scan("org.ppollack.crudandsearch");
    context.refresh();

    PatientDao patientDao = context.getBean(PatientDao.class);
    ApplicationConfiguration appConfig = context.getBean(ApplicationConfiguration.class);

    // upsert a patient into each disparate database
    patientDao.upsert(buildMysqlPatient());
    patientDao.upsert(buildMongoDbPatient());
    appConfig.refreshPatientIndex();

    // demonstrate search across disparate databases
    searchAndPrintResults("pollack", patientDao); // returns just the mongodb record
    searchAndPrintResults("saleh", patientDao);   // returns just the mysqldb record
    searchAndPrintResults("michael", patientDao); // returns both records!
  }


  private static void searchAndPrintResults(String query, PatientDao patientDao) {
    LOG.info("searching for \"" + query + "\"...");
    Page<? extends IPerson> results = patientDao.search(query);

    StringBuilder sb = new StringBuilder();
    sb.append("found ").append(results == null ? 0 : results.getTotalElements()).append(" results:");
    if (results != null) {
      for (IPerson patient : results) {
        sb.append("\n from ").append(patient.getDatasourceName()).append(": ");
        sb.append(patient.getLastName()).append(", ").append(patient.getFirstName());
      }
    }
    sb.append("\n");
    LOG.info(sb.toString());
  }


  private static IPerson buildMongoDbPatient() {
    PatientMongodb patient = new PatientMongodb();
    patient.setId("foo");
    patient.setFirstName("Paul");
    patient.setMiddleName("Michael");
    patient.setLastName("Pollack");

    patient.setMailingAddresses(new ArrayList<>());
    MailingAddress address = new MailingAddress();
    patient.getMailingAddresses().add(address);
    address.setCity("New York City");
    address.setLine1("1 World Trade Center");
    address.setLine2("45th Floor");
    address.setState("NY");
    address.setType(MailingAddressType.WORK);
    return patient;
  }

  private static IPerson buildMysqlPatient() {
    PatientMysql patient = new PatientMysql();
    patient.setId(1L);
    patient.setFirstName("Medhat");
    patient.setMiddleName("Michael");
    patient.setLastName("Saleh");

    patient.setMailingAddresses(new ArrayList<>());
    MailingAddress address = new MailingAddress();
    patient.getMailingAddresses().add(address);
    address.setCity("Lewisville");
    address.setLine1("2301 Chapelwood Dr");
    address.setState("TX");
    address.setPostalCode("75077");
    address.setType(MailingAddressType.WORK);
    return patient;
  }

}
