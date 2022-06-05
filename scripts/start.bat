@REM hdfs
docker-compose -f ..\hdfs\docker-compose.yml start -d
timeout /T 10 /NOBREAK


@REM redis
docker start redis


@REM mongodb
@REM config servers
docker-compose -f ..\mongodb\docker-compose-configsvr.yml start -d
@REM shard servers
docker-compose -f ..\mongodb\docker-compose-shardsvr.yml start -d
@REM mongos
docker-compose -f ..\mongodb\docker-compose-mongos.yml start -d
timeout /T 5 /NOBREAK


@REM app
docker start library