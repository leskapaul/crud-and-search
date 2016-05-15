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
