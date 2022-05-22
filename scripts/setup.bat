
@REM mongo
@REM config servers
docker-compose -f ..\mongodb\docker-compose-configsvr.yml up -d
mongosh --host localhost --port 40011 ..\mongodb\setup-configsvr.js

@REM shard servers
docker-compose -f ..\mongodb\docker-compose-shardsvr.yml up -d
@REM shardsvr-rs1
mongosh --host localhost --port 40021 ..\mongodb\setup-shardsvr-rs1.js
@REM shardsvr-rs2
mongosh --host localhost --port 40031 ..\mongodb\setup-shardsvr-rs2.js

@REM mongos
docker-compose -f ..\mongodb\docker-compose-mongos.yml up -d
timeout /T 5 /NOBREAK
mongosh --host localhost --port 40002 ..\mongodb\setup-mongos.js