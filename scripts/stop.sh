#!/bin/bash

# app
docker stop library


# mongodb
docker-compose -f ../mongodb/docker-compose-mongos.yml stop -v
docker-compose -f ../mongodb/docker-compose-shardsvr.yml stop -v
docker-compose -f ../mongodb/docker-compose-configsvr.yml stop -v


# redis
docker stop redis


# hdfs
docker-compose -f ../hdfs/docker-compose.yml stop -v