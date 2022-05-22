#!/bin/bash

mongoimport --host localhost --port 40002 -d ddbs -c user --file ../data/user.dat
mongoimport --host localhost --port 40002 -d ddbs -c article --file ../data/article.dat
mongoimport --host localhost --port 40002 -d ddbs -c read --file ../data/read.dat