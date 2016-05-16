package org.ppollack.crudandsearch.pathology.mysql;

import org.springframework.data.repository.CrudRepository;

public interface PatientMysqlRepository extends CrudRepository<PatientMysql, Long> {
}
