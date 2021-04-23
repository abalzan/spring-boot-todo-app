Swagger documentation
http://localhost:8080/swagger-ui.html

There are two users:
```
login: user 
password: pwd123
login: admin 
password: pwd123
```
application url: http://localhost:8080/

# Run application using docker

If you want to run the project using docker, first execute a
```
mvn clean install
```

Run your containers:

```$ docker-compose up```
OR
```$ docker-compose up --build  --force-recreate```
