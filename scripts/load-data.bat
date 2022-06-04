
mongoimport --host localhost --port 40002 -d ddbms -c user --file ..\data\user.dat
mongoimport --host localhost --port 40002 -d ddbms -c article --file ..\data\article.dat
mongoimport --host localhost --port 40002 -d ddbms -c read --file ..\data\read.dat
mongosh --host localhost --port 40002 ..\mongodb\generate-beread.js
mongosh --host localhost --port 40002 ..\mongodb\generate-rank.js