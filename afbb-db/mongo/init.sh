#!/bin/sh
mongoimport --db afbb --collection category --drop --file /docker-entrypoint-initdb.d/category.json --jsonArray
mongoimport --db afbb --collection item --drop --file /docker-entrypoint-initdb.d/item.json --jsonArray