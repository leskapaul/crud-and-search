# crud-and-search
Java library that facilitates CRUD and search operations that are agnostic to the underlying data
storage technologies.  This is intended for situations in which there are multiple disparate
data storage technologies that we want to manage via a single application or interface.

This library's intent is to facilitate rich and simple APIs while requiring minimal implementation
per data storage technology; minimally CRUD-by-ID capabilities.
These APIs shall include interfaces to search functionality, backed by search engine technology
such as Elasticsearch.  Writes via the APIs shall propagate updates to the search engine index.
See PatientDao.java for an example.

This project utilizes Docker to facilitate search via Elasticsearch.  Docker may also be used to
demonstrate capabilities by running several disparate data stores for example purposes.
See the Docker Compose files located in this project's root directory for more information.

Please see SampleApplication.java for an executable example.


## Sample Application

1. startup mySQL, MongoDB, and Elasticsearch, via Docker
Download Docker and then in the root of this project, run: docker-compose -f compose-es-mysql-mongo.yml up
Output from each of the three data stores (mySQL, MongoDB, and Elasticsearch) will be displayed.

2. initialize mySQL
run data/mysql/ddl/sample-ddl.sql into mySQL as shown below
```
MacBook-Pro:crud-and-search paulpollack$ mysqlsh --uri root@localhost:3306 -D agillaire -p --sqlc < data/mysql/ddl/sample-ddl.sql
Enter password:
MacBook-Pro:crud-and-search paulpollack$
```

3. run SampleApplication.java
This application will insert a patient record into mySQL, another one into MongoDB, and demonstrate
searches against Elasticsearch that return one, the other, and both records.  Here's what the
output of a successful run looks like:
```
May 17, 2016 11:27:54 PM org.ppollack.crudandsearch.pathology.ApplicationConfiguration postConstruct
INFO: PostConstruct: deleted all records in all data stores
May 17, 2016 11:27:54 PM org.ppollack.crudandsearch.pathology.SampleApplication main
INFO: org.ppollack.crudandsearch.pathology.SampleApplication starting...

May 17, 2016 11:27:54 PM org.ppollack.crudandsearch.pathology.SampleApplication searchAndPrintResults
INFO: searching for "pollack"...
May 17, 2016 11:27:54 PM org.ppollack.crudandsearch.pathology.SampleApplication searchAndPrintResults
INFO: found 1 results:
 from PATIENT_MONGODB: Pollack, Paul

May 17, 2016 11:27:54 PM org.ppollack.crudandsearch.pathology.SampleApplication searchAndPrintResults
INFO: searching for "saleh"...
May 17, 2016 11:27:54 PM org.ppollack.crudandsearch.pathology.SampleApplication searchAndPrintResults
INFO: found 1 results:
 from PATIENT_MYSQL: Saleh, Medhat

May 17, 2016 11:27:54 PM org.ppollack.crudandsearch.pathology.SampleApplication searchAndPrintResults
INFO: searching for "michael"...
May 17, 2016 11:27:54 PM org.ppollack.crudandsearch.pathology.SampleApplication searchAndPrintResults
INFO: found 2 results:
 from PATIENT_MYSQL: Saleh, Medhat
 from PATIENT_MONGODB: Pollack, Paul
```



