

# Start MySQL as docker container

```sh
docker run --name mysql-local -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=tickets -p 3306:3306 -d mysql:latest
```

# Usage example

CURL command for creating a new ticket:

```sh
curl -X POST http://localhost:8088/tickets -H "Content-Type: application/json" -d "{\"eventName\":\"Concert\",\"eventDate\":\"2023-12-01\"}"
```

```sh
curl -X GET http://localhost:8088/gtickets      
```

# Swagger UI

http://localhost:8088/swagger-ui/index.html

