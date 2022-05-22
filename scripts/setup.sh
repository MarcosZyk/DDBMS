#!/bin/bash

# mongo
# config servers
docker-compose -f ../mongodb/docker-compose-configsvr.yml up -d
mongosh --host localhost --port 40011 ../mongodb/setup-configsvr.js

# shard servers
docker-compose -f ../mongodb/docker-compose-shardsvr.yml up -d
# shardsvr-rs1
mongosh --host localhost --port 40021 ../mongodb/setup-shardsvr-rs1.js
# shardsvr-rs2
mongosh --host localhost --port 40031 ../mongodb/setup-shardsvr-rs2.js

# mongos
docker-compose -f ../mongodb/docker-compose-mongos.yml up -d
sleep 5
mongosh --host localhost --port 40002 ../mongodb/setup-mongos.js