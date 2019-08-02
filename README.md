# Cloud Services
Spring Boot with Cloud, Dockers and Kubernetes

Login to ``https://labs.play-with-docker.com/`` with your docker-hub id.

Start a Linux instance

Clone the code repo on to the instance

```
git clone https://github.com/glarimy/cloud-services.git
```

Build the docker image

```
docker build -t glarimy/library .
```

Login to docker-hub and export the image

```
docker login
docker push glarimy/library
```

Get the images from docker-hub

```
docker pull mysql
docker pull glarimy/library
```

Create a network

```
docker network create glarimy
docker network ls
```

Run the docker containers

```
docker container run --name mysqldb --network glarimy -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_DATABASE=glarimy -d mysql
docker container logs -f mysqldb
docker container run --network glarimy --name library -p 8080:8080  glarimy/library
docker container exec -it mysqldb bash
```

Verify the apps

```
curl -X POST -H 'Content-Type: application/json' -i '/library/book' --data '{"isbn":123, "title":"Cloud Services"}'
curl '/library/book/123'
```

Run the docker compose

```
docker-compose up
```

Scale up using Kubernetes

Login to ``https://kubernetes.io/docs/tutorials/kubernetes-basics/create-cluster/cluster-interactive/``

Start MiniKube (Single Node Cluster)

```
minikube start
```

Verify the nodes, pods and services

```
kubectl get nodes
kubectl get pods
kubectl get services
```

kubectl describe pods
kubectl describe services

Launch pod with MySQL

``` 
kubectl create -f mysql-pod.yml
kubectl get pods
kubectl describe pods
kubectl logs mysqldb
kubectl exec -it mysqldb bash
```

Expose MySQL as a service

```
kubectl expose pod mysqldb --port=3306 --name=mysqldb --type=NodePort
kubectl get services
kubectl describe services
```

Launch pod with Library

```
kubectl create -f library-pod.yml
kubectl get pods
kubectl describe pods
kubectl logs library
```

Expose Library as a service

```
kubectl expose pod library --port=8080 --name=library --type=NodePort
kubectl get services
kubectl describe services
```

Access REST services locally

```
minikube ip
kubectl get services
curl <minikube ip>:nodePort/library/book/123
curl -X POST -H 'Content-Type: application/json' -i minikubeob:nodeport/library/book --data '{"isbn":123, "title":"Kubernetes"}'
curl <minikube ip>:nodePort/library/book/123
```

Access REST services externally

```
http://<dns>/library/book/123
```