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

Docker is required, so please download and install before starting.

First, start mySQL, MongoDB, and Elasticsearch, via Docker by running: docker-compose -f compose-es-mysql-mongo.yml up.
Output from each of the three data stores (mySQL, MongoDB, and Elasticsearch) will be displayed.
```
crud-and-search-mysql            | Version: '5.7.12'  socket: '/var/run/mysqld/mysqld.sock'  port: 3306  MySQL Community Server (GPL)
crud-and-search-mongodb          | 2016-05-18T03:46:40.840+0000 I NETWORK  [initandlisten] waiting for connections on port 27017
crud-and-search-elasticsearch    | [2016-05-18 03:46:45,842][INFO ][cluster.routing.allocation] [Greer Grant] Cluster health status changed from [RED] to [GREEN] (reason: [shards started [[patient-index][0]] ...]).
```

Then, run SampleApplication.java.
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



