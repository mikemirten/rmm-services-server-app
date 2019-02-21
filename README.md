# Devices management service

## Requirements
- Java runtime version 1.8 or later 
- Gradle version 4.0 or later
- PostgresQL database version 9.0 or later

## Deployment 
Clone the reposiroty: ```git clone git@github.com:mikemirten/rmm-services-server-app.git```

Setup PostgresQL database.
By default a clean database named "test" expecting at 127.0.0.1:5432 with username and password: "test".
Update ```src/main/resources/application.properties``` if name/credentials are different.

Make sure nothing using port 8080.

Build and run application by: ```gradle bootRun``` command.
The application should be accessible at 127.0.0.1:8080.

The API is secured by Basic WEB authentication. The only existing user is "user" with password "test".

Default customer with ID **1** and service with ID **1** will be created at the application's start.
