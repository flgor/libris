#!/bin/bash

rm ./api-0.0.1-SNAPSHOT.jar
mv /home/c7florin/api-0.0.1-SNAPSHOT.jar .

pgrep -f api-0.0.1-SNAPSHOT.jar | xargs kill

cd /opt/libris
java -jar api-0.0.1-SNAPSHOT.jar --spring.config.location=./app.properties >> api.log &