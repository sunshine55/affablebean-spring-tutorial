Affable Bean Tutorial
=====================

### Rewrite [Netbeans e-commerce tutorial](https://netbeans.org/kb/docs/javaee/ecommerce/intro.html) using Spring framework

1. Designing application:
   * Scenario
   * Requirements
   * Mockups
   * Architecture
2. Setting up development environment:
   * IDE: [Spring Tool Suite](https://spring.io/tools/sts/all)
   * DBMS: MySQL
   * Create a new Spring MVC template project
   * Template engine: Velocity or Thymeleaf; Layout: Bootstrap
3. Preparing views and controllers (front-end).
4. Designing data model:
   * Identify the entities and their relationships
   * Follow bottom up (reverse mapping) or top down approach using Hibernate
5. Developing business logic.
6. Integrating with front-end.
7. Others:
   * Securing the application
   * Adding language support
   * Testing
   * Deploying on [OpenShift](https://www.openshift.com/) -> visit demo app:
   	 - [The front store](http://affablebean-tonyvo.rhcloud.com/)
   	 - [The admin console](http://affablebean-tonyvo.rhcloud.com/admin) (username/password found in `scripts/sql/admin-console_data.sql`)

### Run application using Docker

* Docker Compose
  - Install [Docker Compose](https://docs.docker.com/compose/install/)
  - `docker-compose up -d`
  - To start in the next use: `docker-compose start|stop [affablebean-container|mysql-container]`
* Standalone (given docker installed and currently in `affablebean-spring-tutorial` project folder)
```
docker run -d -v $(pwd)/scripts/sql:/docker-entrypoint-initdb.d -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=affablebean -p 3306:3306 --name mysql-container mysql:5.7.14
docker run -d --link mysql-container -p 8080:8080 --name affablebean-container sunshine55/affablebean
```
OR
```
mvn clean install
docker run -d -v $(pwd)/scripts/sql:/docker-entrypoint-initdb.d -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=affablebean -p 3306:3306 --name mysql-container mysql:5.7.14
docker build -t affablebean .
docker run -d --link mysql-container -p 8080:8080 --name affablebean-container affablebean
```
THEN
```
docker start|stop mysql-container
docker start|stop affablebean-container
```