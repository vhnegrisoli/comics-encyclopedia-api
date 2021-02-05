# Comics Encyclopedia API

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
* Axios
* Mongoose

## Architecture

* REST API architecture
* Microservices architecture

## Infrastructure locally

* Docker
* Docker-compose

## CI/CD for deploying

* Heroku

## Architecture design

This diagram represents how the components works in the system, and the execution flow. 

![Architecture design](https://github.com/vhnegrisoli/comics-encyclopedia-api/blob/main/Comics%20Encyclopedia%20Architecture.png)

## Author

* Victor Hugo Negrisoli
* Back-end Software Developer
* [LinkedIn](https://www.linkedin.com/in/victorhugonegrisoli/)
