Setup Liquibase
===============

1) Installation
use brew:  brew install liquibase
or download jar and add it to your path 

2) Validate your installation by running:
  liquibase --classpath=<location driver>/postgresql.jar validate 

3) If running validate fails try running with debug:
  liquibase --classpath=<location driver>/postgresql.jar --logLevel=DEBUG validate
 

Creating the Database
===================

This will create database.

  ./init-oht-db.sh


Dropping everything
======================
From the command line
  liquibase --classpath=<location driver>/postgresql.jar dropAll


Schema Update
==================
PGJARLOCATION=$HOME
liquibase --classpath=$PGJARLOCATION/postgresql.jar update

e.g. liquibase --classpath=/Users/peterwhitehead/.gradle/caches/modules-2/files-2.1/org.postgresql/postgresql/42.0.0/938ede0cdf862cc4eaba5023a86254783af1d261/postgresql-42.0.0.jar update


Testing rollback
=====================
-- Assuming a tag has been created in xml
--     <changeSet author="pwhitehead" id="tag_ohtd_001_1">
--           <tagDatabase tag="ohtd_001_1"/>
--       </changeSet>
liquibase --classpath=$PGJARLOCATION/postgresql.jar rollback ohtd_001_1

Dummy Data
=====================
File dummy_data.sql contains some test data. 