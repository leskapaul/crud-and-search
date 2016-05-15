package org.ppollack.crudandsearch.pathology.common.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.ppollack.crudandsearch.exception.CrudException;
import org.ppollack.crudandsearch.pathology.common.model.IPerson;
import org.ppollack.crudandsearch.pathology.elasticsearch.PatientElasticsearchDao;
import org.ppollack.crudandsearch.pathology.mongodb.PatientMongodb;
import org.ppollack.crudandsearch.pathology.mysql.PatientMysql;
import org.springframework.data.domain.Page;

public class PatientDaoTest {

  private PatientDao dao = new PatientDao(buildSearchDao());

  private PatientElasticsearchDao buildSearchDao() {
    // TODO need to fix this now that mock impl was replaced by real impl
    return null;
  }

  @Test
  public void upsertMysqlThenSearchByLastName() {
    upsertAndThenSearchByLastName(PatientCrudDao.PATIENT_MYSQL, "someone named medhat");
  }

  @Test
  public void upsertMongodbThenSearchByLastName() {
    upsertAndThenSearchByLastName(PatientCrudDao.PATIENT_MONGODB, "pollack is a type of fish");
  }

  @Test
  public void upsertBothThenSearch() {
    IPerson mysqlPatient = upsertAndAssert(PatientCrudDao.PATIENT_MYSQL);
    IPerson mongodbPatient = upsertAndAssert(PatientCrudDao.PATIENT_MONGODB);

    Page<? extends IPerson> searchResults = dao.search("medhat paul");
    assertNotNull(searchResults);
    assertEquals(2, searchResults.getTotalElements());

    deleteAndAssert(PatientCrudDao.PATIENT_MYSQL, mysqlPatient);
    deleteAndAssert(PatientCrudDao.PATIENT_MONGODB, mongodbPatient);
  }

  private void upsertAndThenSearchByLastName(PatientCrudDao patientCrudDao, String searchQuery) {
    IPerson patient = upsertAndAssert(patientCrudDao);

    Page<? extends IPerson> searchResults = dao.search(searchQuery);
    assertNotNull(searchResults);
    assertEquals(1, searchResults.getTotalElements());
    IPerson persistedPatient = searchResults.iterator().next();
    assertPersistedPatient(patient, persistedPatient);

    deleteAndAssert(patientCrudDao, persistedPatient);
  }

  private void deleteAndAssert(PatientCrudDao patientCrudDao, IPerson patient) {
    try {
      dao.delete(patient);
    } catch (CrudException e) {
      throw new RuntimeException(e);
    }
    patient = dao.get(patientCrudDao.name(), patient.getId());
    assertNull(patient);
  }

  private IPerson upsertAndAssert(PatientCrudDao patientCrudDao) {
    IPerson patient;
    switch (patientCrudDao) {
      case PATIENT_MYSQL:
        patient = buildTestMysqlPatient();
        break;
      case PATIENT_MONGODB:
        patient = buildTestMongodbPatient();
        break;
      default:
        throw new IllegalArgumentException("unknown " + PatientCrudDao.class.getSimpleName() + " value");

    }

    try {
      dao.upsert(patient);
    } catch (CrudException e) {
      throw new RuntimeException(e);
    }

    IPerson persistedPatient = dao.get(patientCrudDao.name(), patient.getId());
    assertPersistedPatient(patient, persistedPatient);
    return patient;
  }

  private IPerson buildTestMongodbPatient() {
    PatientMongodb patient = new PatientMongodb();
    patient.setId("foo");
    patient.setFirstName("Paul");
    patient.setLastName("Pollack");
    return patient;
  }

  private IPerson buildTestMysqlPatient() {
    PatientMysql patient = new PatientMysql();
    patient.setId(1L);
    patient.setFirstName("Medhat");
    patient.setLastName("Saleh");
    return patient;
  }

  private void assertPersistedPatient(IPerson patient, IPerson persistedPatient) {
    assertNotNull(persistedPatient);
    assertEquals(patient.getId(), persistedPatient.getId());
    assertEquals(patient.getFirstName(), persistedPatient.getFirstName());
    assertEquals(patient.getLastName(), persistedPatient.getLastName());
  }
}
