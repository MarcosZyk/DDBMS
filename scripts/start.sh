#!/bin/bash

# hdfs
docker-compose -f ../hdfs/docker-compose.yml start -d
sleep 10


# redis
docker start redis


# mongodb
# config servers
docker-compose -f ../mongodb/docker-compose-configsvr.yml start -d
# shard servers
docker-compose -f ../mongodb/docker-compose-shardsvr.yml start -d
# mongos
docker-compose -f ../mongodb/docker-compose-mongos.yml start -d
sleep 5


# app
docker start library