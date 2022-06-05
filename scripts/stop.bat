@REM app
docker stop library


@REM mongodb
docker-compose -f ..\mongodb\docker-compose-mongos.yml stop -v
docker-compose -f ..\mongodb\docker-compose-shardsvr.yml stop -v
docker-compose -f ..\mongodb\docker-compose-configsvr.yml stop -v


@REM redis
docker stop redis


@REM hdfs
docker-compose -f ..\hdfs\docker-compose.yml stop -v