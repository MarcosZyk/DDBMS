
docker pull redis:latest

docker run -itd --name redis -p 6379:6379 redis

docker network connect hdfs_hadoopnet redis --ip 172.21.0.6