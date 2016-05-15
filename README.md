# crud-and-search
Java library that facilitates CRUD and search operations that are agnostic to the underlying data
storage technologies.  This is intended for situations in which there are multiple disparate
data storage technologies that we want to manage via a single application or interface.

The idea is to offer a rich and easy to use API while requiring minimal implementation per
data storage technology; generally just CRUD-by-ID and get-all capabilities.
This library is intended to offer an API to such implementations.
This API shall include an interface to search functionality, backed by search engine technology
(e.g. Elasticsearch).  Writes via the API around CRUD-by-ID functionality shall propagate
updates to the search engine index.  See PatientDao.java for an example.

Coming soon...
This project utilizes Docker to facilitate search via Elasticsearch.  Docker is also used to
demonstrate capabilities by running several disparate data stores for example purposes.
Docker Compose file "compose-mysql-mongodb.yml" is an example.  This Docker Compose
will run four containers: mySQL, MongoDB, Elasticearch, and crud-and-search.

NOTE: This project is currently in development and not yet fully functional.