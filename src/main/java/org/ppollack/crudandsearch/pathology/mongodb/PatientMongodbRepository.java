package org.ppollack.crudandsearch.pathology.mongodb;

import org.springframework.data.repository.CrudRepository;

public interface PatientMongodbRepository extends CrudRepository<PatientMongodb, String> {
}
