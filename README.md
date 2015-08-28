# Spring Cloud Polyglot Persistence Example Project

An example project that demonstrates an end-to-end cloud-native application with multiple backing services using Spring Cloud.

Demonstrated concepts:

* Polyglot persistence
* Service discovery
* API gateway

## Docker

Each service is built and composed using Docker. A docker compose file allows you to run this example locally on your machine. The recommended system memory for this example is about 5GB.

## Polyglot Persistence

One of the core concepts of this example project is how polyglot persistence can be approached in practice. Microservices in the project use their own database, while integrating with the data from other services through REST or a message bus.

* Neo4j (graph)
* MySQL (relational)

## Movie Ratings

This example project focuses on movies and ratings. Take a look at the companion blog post listed here: [Polyglot Persistence with Spring Cloud and Docker](http://www.kennybastani.com)

### Data Services

![http://i.imgur.com/aQYGZFy.png](http://i.imgur.com/aQYGZFy.png)

### Domain Data

![http://i.imgur.com/VlwSw2q.png](http://i.imgur.com/VlwSw2q.png)

In the graph data model above we can see the common entities that we need to expose from our services. The nodes represent the domain entities within our movie application.

* User
* Movie
* Genre

The connections between these entities give us a good idea of our boundaries that we need to consider when designing our microservices. For instance, we may have a requirement to analyze the ratings data between movies and users to generate movie recommendations.

For this example project we will use three data services:

* Rating Service (Neo4j)
* Movie Service (MySQL)
* User Service (Neo4j)

## Microservice Architecture

This example project demonstrates how to build a new cloud-native application using microservices. Since each microservice in the project is a module of a single parent project, developers have the advantage of being able to run and develop with each microservice running on their local machine.

## Service discovery

This project contains two discovery services, one on Netflix Eureka, and the other uses Consul from Hashicorp. Having multiple discovery services provides the opportunity to use one (Consul) as a DNS provider for the cluster, and the other (Eureka) as a proxy-based API gateway.

## API Gateway

Each microservice will coordinate with Eureka to retrieve API routes for the entire cluster. Using this strategy each microservice in a cluster can be load balanced and exposed through one API gateway. Each service will automatically discover and route API requests to the service that owns the route. This proxying technique is equally helpful when developing user interfaces, as the full API of the platform is available through its own host as a proxy.

## Run it on PivotalCF

* Requirements: Maven 3, Java 8, Docker, Docker Compose

* Download Docker if you haven’t already. Follow the instructions found [here] (https://docs.docker.com/installation/), to get Docker up and running on your development machine. You will also need to install [Docker Compose] (https://docs.docker.com/compose/), the installation guide can be found [here] (https://docs.docker.com/compose/install/)

* Clone the repo: 
    
> $ git clone https://github.com/kbastani/spring-cloud-polyglot-persistence-example.git

* Building the project:

To build the project, from the terminal, run the following command at the root of the project.

> $ mvn clean install (from project root dir)

* Push to PivotalCF

Edit manifest.yml to use your domain name instead of cfapps.io

Make sure a MySQL service instance is present in the space you are pushing to, with name "mysql" 

> $ cf push (from the project root dir)

That's it!


# License

This project is licensed under Apache License 2.0.
