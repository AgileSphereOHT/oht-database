 #!/bin/bash
 set -e
 
 psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE USER oht_user PASSWORD 'klk34sNUlf0eD';
    CREATE DATABASE oht_database;
    GRANT ALL PRIVILEGES ON DATABASE oht_database TO oht_user;
EOSQL
