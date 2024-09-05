#!/usr/bin/bash

./mvnw clean package

docker compose build
docker compose up