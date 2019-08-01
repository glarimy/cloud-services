# cloud-services
Spring Boot with Cloud, Dockers and Kubernetes

Login to https://labs.play-with-docker.com/ with your docker-hub id.

Start a Linux instance

Clone the code repo on to the instance
git clone https://github.com/glarimy/cloud-services.git

Build the docker image
docker build -t glarimy/library .

Login to docker-hub and export the image
docker login
docker push glarimy/library

Get the images from docker-hub
docker pull mysql
docker pull glarimy/library

Create a network
docker network create glarimy
docker network ls

Run the docker containers
docker container run --name mysqldb --network glarimy -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_DATABASE=glarimy -d mysql
docker container logs -f mysqldb
docker container run --network glarimy --name library -p 8080:8080  glarimy/library
docker container exec -it mysqldb bash

curl -X POST -H 'Content-Type: application/json' -i '/library/book' --data '{"isbn":123, "title":"Cloud Services"}'
curl '/library/book/123'

Run the docker compose
docker-compose up


