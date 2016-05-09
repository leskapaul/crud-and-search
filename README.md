# crud-and-search
Java library that facilitates CRUD and search operations that are agnostic to the underlying data
storage technologies.  This is intended for situations in which there are multiple disparate
data storage technologies that we want to manage via a single application or interface.

The idea is to offer a rich and easy to use API while requiring minimal implementation per
data storage technology; generally just CRUD-by-ID and get-all capabilities.
This library is intended to offer an API to such implementations.
This API shall include an interface to search functionality, backed by search engine technology
(e.g. Elasticsearch).  Writes via the API around CRUD-by-ID functionality shall propagate
updates to the search engine index.

This project uses Docker to demonstrate capabilities.  For example, by running one of the Docker
Compose files at the root of this project a number of Docker containers will be run.  These
containers include multiple, disparate data storage technologies, a search engine, and a
container running the crud-and-search library with a REST interface.

NOTE: This project is currently in development and not yet fully functional.