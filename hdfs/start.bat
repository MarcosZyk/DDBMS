
docker-compose up -d

timeout /T 10 /NOBREAK

docker exec -it namenode /bin/bash -c 'hadoop dfs -put /hdfs/resource/ /ddbms/resource/'
