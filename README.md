# projektGospodarka

Build using "mvn clean package".

Run using "java -jar backend-1.0-SNAPSHOT.jar".

Tomcat included in jar, starts server on localhost:8080.

Works with H2 database for now.

## What's working?
1. /register - accepts User JSON, returns error if user with given login already exists
2. /user/get - finds user by token, returns Contact JSON or error if user not found
3. /offers/upload - accept Offer JSON (user token is needed)
4. /offers/all - returns JSON array of Offer
