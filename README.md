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

The API is secured by [basic access authentication](https://en.wikipedia.org/wiki/Basic_access_authentication). The only existing user is "user" with password "test".

Default customer with ID **1** and service with ID **1** will be created at the application's start.

## Request/Response Format
The API using a simple document with data wrapped into "data"-field and errors wrapped into "errors"-field.

An example of document with single object:
```javascript
{
  "data": {
    "id": 1,
      "name": "Dell Precission 7820",
      "type": "WINDOWS_WORKSTATION"
  }
}
```
With a collection of objects:
```javascript
{
  "data": [
    {
      "id": 1,
      "name": "Dell Precission 7820",
      "type": "WINDOWS_WORKSTATION"
    },
    {
      "id": 2,
      "name": "PowerEdge T640",
      "type": "WINDOWS_SERVER"
    }
  ]
}
```
With validation errors:
```javascript
{
  "errors": [
    {
      "title": "Constraint Violation",
      "details": "Cannot be empty",
      "path": "data.name"
    }
  ]
}
```

## Endpoints
Get list of all available services:
```javascript
GET /api/v1/services
```

Get list of selected services by certain customer:
```javascript
GET /api/v1/customers/{customerId}/services
```

Add service required by customer:
```javascript
POST /api/v1/customers/{customerId}/services/{serviceId}
```

Remove service cancelled by customer:
```javascript
DELETE /api/v1/customers/{customerId}/services/{serviceId}
```

Calculate cost of services:
```javascript
DELETE /api/v1/customers/{customerId}/calculate-cost
```

Get list of devices of certain customer:
```javascript
GET /api/v1/customers/{customerId}/devices
```

Create new device for certain customer:
```javascript
POST /api/v1/customers/{customerId}/devices
```

Get device by ID:
```javascript
GET /api/v1/devices/{deviceId}
```

Update existing device:
```javascript
PATCH /api/v1/devices/{deviceId}
```

Delete existing device:
```javascript
DELETE /api/v1/devices/{deviceId}
```
