# Comics Encyclopedia API

![Logo](https://github.com/vhnegrisoli/comics-encyclopedia-api/blob/main/API%20Logo.png)

Comics Encyclopedia API is a REST API project using microservices architecture to create a comic book encyclopedia based on Super Hero API.

## Objectives

Well, everyone who knows me personally knows that I love comic books and super heroes, specially DC Comics. I'm a comic book reader and collector since 2013, and now I had the idea of building a REST API using concepts of distributed systems and microservices using synchronous and asynchronous tasks such as messaging processing with Apache Kafka (I already know RabbitMQ, but I choose Kafka for learning a new data streamming and messaging technology).

For the two APIs, I'll use Java 11 with Spring Boot for the main API, which is the API that we will consume, and I'll also use Node.js with Express.js and ES6 Modules (with Sucrase) for building the comic book data (from Super Hero API) processing API, which will be only consumed by the main API and communicated with message topics using Kafka.

## Stack

* Java 11
* Spring Boot 2
* Javascript ES6
* Node.js
* MongoDB
* Apache Kafka
* Spring Data MongoDB
* Spring Data OpenFeign
* Mongoose
* Kafdrop

## Architecture

* REST API architecture
* Microservices architecture

## Infrastructure locally

* Docker
* Docker-compose

## Architecture design

This diagram represents how the components works in the system, and the execution flow. 

![Architecture design](https://github.com/vhnegrisoli/comics-encyclopedia-api/blob/main/Comics%20Encyclopedia%20Architecture.png)

## Run application

As we use a docker-compose file, to run everything you just have to type:

`docker-compose up --build`

In yout terminal at the same directory as the docker-compose file exists.

And if you doesn't want to see the logs of each container during initialization, just add the `-d` flag at the end of the command. Ex: `docker-compose up --build -d`

## Access applications

After running the docker-compose file, there will be 6 docker containers, and it will be possible to access at:

* zookeper                ->  http://localhost:2181
* kafka                   ->  http://localhost:9092
* mongodb                 ->  http://localhost:27017
* kafdrop                 ->  http://localhost:19000
* comics-encyclopedia-api ->  http://localhost:8080
* comics-processor-api    ->  http://localhost:8081

## Kafka Topics

We'll have 4 topics:

* **dc_comics_request.topic** (for DC Comics data processing)
* **dc_comics_response.topic** (for DC Comics data response to Comics Encyclopedia API)
* **marvel_comics_request.topic** (for Marvel Comics data processing)
* **marvel_comics_response.topic** (for Marvel Comics data response to Comics Encyclopedia API)

## Creating topics manually

All of the topics are automatically created after any of the microservices starts. 
But, if you want to create the topics manually, use those commands: 

DC Comics Request:

`docker-compose exec kafka kafka-topics --create --topic dc_comics_request.topic --partitions 1 --replication-factor 1 --if-not-exists --zookeeper zookeeper:2181`

DC Comics Response:

`docker-compose exec kafka kafka-topics --create --topic dc_comics_response.topic --partitions 1 --replication-factor 1 --if-not-exists --zookeeper zookeeper:2181`

Marvel Comics Request: 

`docker-compose exec kafka kafka-topics --create --topic marvel_comics_request.topic --partitions 1 --replication-factor 1 --if-not-exists --zookeeper zookeeper:2181`

Marvel Comics Response:

`docker-compose exec kafka kafka-topics --create --topic marvel_comics_response.topic --partitions 1 --replication-factor 1 --if-not-exists --zookeeper zookeeper:2181`

And if you don't want to use CLI, by accessing Kafdrop you can also create or delete topics.

## Monitor your Kafka with Kafdrop

By accessing http://localhost:19000, you'll be able to monitor your Apache Kafka with Kafdrop. 

![Kafdrop](https://github.com/vhnegrisoli/comics-encyclopedia-api/blob/main/Kafdrop%20Monitor.png)

It'll be possible to monitor your topics, your Kafka specs, and the messages in each topic.

## Author

* Victor Hugo Negrisoli
* Back-end Software Developer
* [LinkedIn](https://www.linkedin.com/in/victorhugonegrisoli/)
