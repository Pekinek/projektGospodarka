# projektGospodarka

Build using "mvn clean package".

Run using "java -jar backend-1.0-SNAPSHOT.jar".

Tomcat included in jar, start server on localhost:8080.

Works with H2 database for now.

## What's working?
1. /register - accept User JSON, returns error if user with given login already exists
2. /user/get - find user by token, return Contact or error if user not found
