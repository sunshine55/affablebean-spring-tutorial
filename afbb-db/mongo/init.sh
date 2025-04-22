#!/bin/sh
mongoimport --db afbb --collection category --drop --file /docker-entrypoint-initdb.d/category.json --jsonArray