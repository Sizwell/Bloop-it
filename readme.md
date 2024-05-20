# Bloop-it

## Overview

This Spring Boot microservices application consists of the following services:

* __API Gateway:__ Acts as a single entry point for external and internal users.

* __Client Request Service:__ Handles external user requests to process a string of text.

* __Discovery Server:__ Manages service registration and discovery.

* __Sensitive Words Service:__ Allows internal users to perform CRUD operations on sensitive words.
  The application is load balanced to ensure high availability and reliability.


## Services
### API Gateway ###
* __Description:__ Acts as the gateway for all external and internal requests. Routes requests to the appropriate microservice.
* __Technologies:__ Spring Cloud Gateway, Eureka Client

### Client Request Service ###
* __Description:__ Accepts a string of text from external users and processes it.
* __Endpoints:__ POST /bloop: Accepts a JSON payload containing a string of text to process.
* __Technologies:__ Spring Boot, Eureka Client

### Discovery Server ###
* __Description:__ Manages the service registry, allowing microservices to discover each other.
* __Technologies:__ Spring Cloud Netflix Eureka Server

### Sensitive Words Service ###
* __Description:__ Provides CRUD operations for managing sensitive words. Accessible to internal users.
* __Endpoints:__
    * __GET /api/v2/sensitveWords/words:__ Retrieve all sensitive words.
    * __GET /api/v2/sensitveWords/word/search:__ Retrieve a sensitive word by searching the word.
    * __POST /api/v2/sensitveWords/words:__ Add sensitive words from the .txt file.
    * __POST /api/v2/sensitveWords/word:__ Add a new sensitive word.
    * __PUT /api/v2/sensitveWords/word/{id}:__ Update an existing sensitive word.
    * __DELETE /api/v2/sensitveWords/word/{id}:__ Delete a sensitive word by ID.

* __Technologies:__ Spring Boot, Spring Data JPA, Eureka Client


## Load Balancing ##
The application uses Spring Cloud LoadBalancer to distribute incoming requests across multiple instances of the client-request-service and sensitive-words-service to ensure high availability and scalability.

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.

## Prerequisites ##
* Java 11 or higher
* Maven

## Usage ##
### External Users: ###
* __Endpoint:__ POST /process
    * __Payload__
```java
{
  "word": "sample text"
}
```

### Internal Users ###
Internal users can manage sensitive words through the sensitive-words-service.

* __Endpoints__
    * __GET /api/v2/sensitveWords/words:__ Retrieve all sensitive words.
    * __GET /api/v2/sensitveWords/word/search:__ Retrieve a sensitive word by searching the word.
    * __POST /api/v2/sensitveWords/words:__ Add sensitive words from the .txt file.
    * __POST /api/v2/sensitveWords/word:__ Add a new sensitive word.
    * __PUT /api/v2/sensitveWords/word/{id}:__ Update an existing sensitive word.
    * __DELETE /api/v2/sensitveWords/word/{id}:__ Delete a sensitive word by ID.

## Configuration ##
__application. Properties__

Each service has its own application.properties file located in the src/main/resources directory. Customize these properties as needed.

## Eureka Client Configuration ##

Ensure that each service has the correct Eureka client configuration to register with the Discovery Server:

```
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/
```

## Eureka Client Configuration ##
see `application.properties` of API Gateway

## Conclusion ##
This Spring Boot microservices application is designed to handle external user requests and internal CRUD operations efficiently, with high availability through load balancing and service discovery. Follow the steps provided to set up and run the application.

## Architecture ##
![Alt Text](https://github.com/Sizwell/Bloop-it/blob/develop/sensitive-word-service/src/main/resources/Architecture.png)


[MIT](https://choosealicense.com/licenses/mit/)