
docker rmi library:dev

docker build -t library:dev .

docker run -d --name library -p 8080:8080 library:dev

docker network connect docker-hdfs-trial_hadoopnet library