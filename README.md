# Overseas Healthcare Service - Database Microservice

## Overview

The following project is part of a solution for an Overseas Healthcare service commissioned by the UK Department of Health.

With an initial focus on provision for state pensioners living outside their country of origin, it will progressively provide a replacement for the existing Medical Benefits system currently being used to provide overseas healthcare support.

A key requirement of the proposed development is that it is capable of interfacing with the RINA product, developed by EESSI, which acts as the ‘middle man’ in interactions between different member states for registering foreign nationals and reimbursement of claims. 

The initial development has successfully delivered a demonstration of this interface working against a deployed instance of RINA, and a front end that is in line with the S1 Request and Registration ‘happy paths' of the prototype being tested against user needs.

## Solution services

The current solution has been developed as three REST based micro services built using Spring Boot running on an embedded Tomcat web container.

[oht-frontend](https://github.com/AgileSphereOHT/oht-frontend)

[oht-database](https://github.com/AgileSphereOHT/oht-database) (this project)

[oht-rina-registration](https://github.com/AgileSphereOHT/oht-rina-registration)

There is also a common shared library containing the definitions of system domain data objects.

[oht-common](https://github.com/AgileSphereOHT/oht-common)


## Technologies being used across projects

- Spring MVC
- Thymeleaf
- Spring Security
- OAuth2
- JPA
- Liquibase
- PostgreSQL
- Gradle
- Lombok
- Swagger

## This project

The oht-database microservice provides a RESTful interface to the PostgreSQL relational database for the Overseas Healthcare solution.

The building of a database for development purposes is documented in a sub project README under /oht-database-liquibase.

Database operations are implemented via standard Spring JPA.
RESTful requests are routed to Controller methods which delegate DB operations to a service layer.
Incoming and outgoing domain objects mapped to DB tables via JPA entities managed by a table specific CrudRepository.

Liquibase is being used to provide a repeatable version specific build of the database schema and population of a base data set.

The Restful interface is documented via Swagger ( http://{baseurl}/swagger-ui.html )

## Build

Assumes local installation of PostgreSQL and Gradle

Follow the instructions in the README file in the oht-database-liquibase directory, providing passwords for the <oht_user> schema user in liquibase.properties and init-ohs-db.sh
From project root

    gradle clean                to clear build dir
    gradle test                 to compile and run unit tests
    gradle jar                  to build jar file
    gradle build                to compile, run tests and create jar file
    gradle publishToMavenLocal  to build and install jar in local maven repo
    gradle bootrun              to start up microservice in embedded tomcat container

## License

This document is released under [CC0](LICENSE.md).

## See also 

https://oh-alpha-confluence.atlassian.net/wiki/display/OHA/Setting+up+development+environment
