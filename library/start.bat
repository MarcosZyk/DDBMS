docker rmi library:dev

docker build -t library:dev .

docker run --name library -p 8080:8080 library:dev
