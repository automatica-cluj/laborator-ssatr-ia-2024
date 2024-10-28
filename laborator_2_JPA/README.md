
# Introduction

This is a simple Spring Boot application that provides RESTful API for creating and retrieving tickets. 

## Start MySQL as docker container

```sh
docker run --name mysql-local -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=tickets -p 3306:3306 -d mysql:latest
```

## Configuration 

Check application.properties file for configuration details. Make user, user, password and database names are correct. 

## Usage example

CURL command for creating a new ticket:

```sh
curl -X POST http://localhost:8088/tickets -H "Content-Type: application/json" -d "{\"eventName\":\"Concert\",\"eventDate\":\"2023-12-01\"}"
```

```sh
curl -X GET http://localhost:8088/tickets      
```

## Swagger UI

http://localhost:8088/swagger-ui/index.html

# Exercises

## Exercice 1 

Update ticket creation to include a ticket price and category. Update the ticket retrieval to include the ticket category as an optional filter. 

## Exercice 2 

Add a new endpoint and required logic to allow a user to purchase a ticket. For example an entity called Purchase with fields: ticketId, userId, purchaseDate could be used to store the purchase information.

## Exercise 3

Implement a checkin mechanism for tickets. Add a new endpoint and required logic to allow a user to checkin a ticket.

## Exercise 4

Extend the purchase endpoint to include a payment gateway integration. For example, a payment gateway could be simulated by a simple RESTful service that accepts a payment request and returns a payment confirmation. How will you manage the payment status in the ticketing system? 

## Exercise 5

How will you integrate a client application with the ticketing system? 
- For example, a mobile application that allows users to purchase tickets.
- ALternatively, a web application that allows users to purchase tickets.
- Or a Java desktop application that allows users to purchase tickets. For this last particular case, you can use Swing and a rest client library to communicate with the ticketing system.