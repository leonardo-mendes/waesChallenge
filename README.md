## Waes Challenge (Assignment Scalable Web)
 The goal of this assignment is to show your coding skills and what you value in software engineering. We value new ideas so next to the original requirement feel free to improve/add/extend. We evaluate the assignment depending on your role (Developer/Tester) and your level of seniority.

**The assignment** 
-  Provide 2 http endpoints that accepts JSON base64 encoded binary data on both endpoints o /v1/diff/{id}/left and /v1/diff/{id}/right .
-  The provided data needs to be diff-ed and the results shall be available on a third end point o /v1/diff/{id}/result .
-  The results shall provide the following info in JSON format o If equal return that o If not of equal size just return that o If of same size provide insight in where the diffs are, actual diffs are not needed (So mainly offsets + length in the data). 
-  Make assumptions in the implementation explicit, choices are good but need to be communicated.

**Must haves** 
- Solution written in Java.
-  Internal logic shall be under unit test.
- Functionality shall be under integration test.
- Documentation in code.
- Clear and to the point readme on usage.


## Solution [![Build Status](https://travis-ci.org/leonardo-mendes/waesChallenge.svg?branch=master)](https://travis-ci.org/leonardo-mendes/waesChallenge)

The [solution](https://github.com/leonardo-mendes/waesChallenge) was built with Java 8 + Spring Boot 2.1.0, using a multi modules pattern with TDD (Test Driven Development) and other tools like JPA, Hibernate, JUnit, Mockito, MySql, Docker Compose, Travis CI and Swagger2.

#### Requirements
- Install Java 8.
- Install Git.
- Install Maven.
- Install Docker Compose.

#### Executing the Project
- Clone the [Project](https://github.com/leonardo-mendes/waesChallenge).
- Open terminal and execute: **make start** (Linux).
- Open cmd and execute: **docker-compose up** (Windows).
- Execute mvn clean install.
- Execute the Application.java in project.

## Api Documentation

**Swagger**
The evolution of your API’s functionality is inevitable, but the headache of maintaining API docs doesn’t have to be. Swagger tools takes the hard work out of generating and maintaining your API docs, ensuring your documentation stays up-to-date as your API evolves.

To explore the api documentation, is necessary run the project and access:
> http://localhost:8080/swagger-ui.html - [Preview](https://uploaddeimagens.com.br/images/001/706/130/original/swagger.jpg?1541356229)
