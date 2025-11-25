# Cloud-based application: Assignment 1

## Part 2: Building and deploying the application in docker

### Introduction

For this second part of the assignment I've decided to build both `Dockerfile`, `docker-compose.yml` in order to be able to build up the docker image easily with a multistage Dockerfile and to easy running my docker image in a container using the docker-compose file.

I've also decided to create a docker-image in the docker-hub in order to easy up the deployment of the application in an environment such as the one in the third link and also to simulate a real-world application. You can access the docker repository in docker hub via the following link: `https://hub.docker.com/r/andrem3007/cloud-based-applications-hw1`.

### How to build and run the application in docker locally

In order to build the docker image locally and run it in your system, you shall use the following commands:

```bash
docker compose up --build
```

This command will build the application from scratch using the multi-stage Dockerfile build and will deploy it run it in a docker container specified in the docker-compose file.

**Testing the application:**

You will then be able to access the web-service via the port `8080`, here is an example of an url that could be used to get some information from the web-service: `http://localhost:8080/api/getPopulation?year=1995`