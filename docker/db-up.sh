#!/bin/sh
docker-compose --project-name base -f docker/docker-compose-base.yml up -d
#--no-recreate
echo "docker instance up"

RESULT=$(docker exec -it base psql -U base_user db_sistema_academico -c "SELECT 1 FROM pg_roles WHERE rolname='base_user'" | grep -a "1 row" )
if [ -z "$RESULT" ]
then
    echo "base_user user not found. Creating: base_user"
    cat docker/create_user.sql | docker exec -i base psql -U postgres db_sistema_academico
else
    echo "base user exists. It's all good!"
fi





