
@REM shutdown
docker-compose -f ..\mongodb\docker-compose-mongos.yml down -v
docker-compose -f ..\mongodb\docker-compose-shardsvr.yml down -v
docker-compose -f ..\mongodb\docker-compose-configsvr.yml down -v
@REM remove volumes
docker-compose -f ..\mongodb\docker-compose-mongos.yml down -v
docker-compose -f ..\mongodb\docker-compose-shardsvr.yml down -v