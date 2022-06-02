
docker-compose up -d

timeout /T 10 /NOBREAK

docker exec -it namenode hadoop fs -mkdir /articles/
docker exec -it namenode hadoop fs -put /hdfs/articles/ /articles/
