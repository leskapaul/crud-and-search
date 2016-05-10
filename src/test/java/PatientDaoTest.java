import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.ppollack.crudandsearch.PatientCrudDao;
import org.ppollack.crudandsearch.PatientDao;
import org.ppollack.pathology.model.IPerson;
import org.ppollack.pathology.mongodb.PatientMongodb;
import org.ppollack.pathology.mysql.PatientMysql;

import java.util.List;

public class PatientDaoTest {

  private PatientDao dao = new PatientDao();

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

    List<IPerson> searchResults = dao.search("medhat paul");
    assertNotNull(searchResults);
    assertEquals(2, searchResults.size());

    deleteAndAssert(PatientCrudDao.PATIENT_MYSQL, mysqlPatient);
    deleteAndAssert(PatientCrudDao.PATIENT_MONGODB, mongodbPatient);
  }

  private void upsertAndThenSearchByLastName(PatientCrudDao patientCrudDao, String searchQuery) {
    IPerson patient = upsertAndAssert(patientCrudDao);

    List<IPerson> searchResults = dao.search(searchQuery);
    assertNotNull(searchResults);
    assertEquals(1, searchResults.size());
    IPerson persistedPatient = searchResults.get(0);
    assertPersistedPatient(patient, persistedPatient);

    deleteAndAssert(patientCrudDao, persistedPatient);
  }

  private void deleteAndAssert(PatientCrudDao patientCrudDao, IPerson patient) {
    dao.delete(patient);
    patient = dao.get(patientCrudDao, patient.getId());
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

    dao.upsert(patient);

    IPerson persistedPatient = dao.get(patientCrudDao, patient.getId());
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
    patient.setFname("Medhat");
    patient.setLname("Saleh");
    return patient;
  }

  private void assertPersistedPatient(IPerson patient, IPerson persistedPatient) {
    assertNotNull(persistedPatient);
    assertEquals(patient.getId(), persistedPatient.getId());
    assertEquals(patient.getFirstName(), persistedPatient.getFirstName());
    assertEquals(patient.getLastName(), persistedPatient.getLastName());
  }
}
