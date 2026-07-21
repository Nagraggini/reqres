
![Top Language](https://img.shields.io/github/languages/top/Nagraggini/reqres)
![Rest Assured](https://img.shields.io/badge/Rest%20Assured-API-orange) ![License](https://img.shields.io/badge/license-MIT-green)


## REST API Tests: Reqres

This repository contains an automated REST API test suite for the [Reqres API](https://reqres.in/).

The project demonstrates API testing using REST Assured, JUnit 5, Maven.

## Toolbox

- Programming language: Java 21
- Test automation framework: JUnit 5
- API testing framework: REST Assured
- Build tool: Maven

Requirements:
- JDK 21+
- Maven 3.x
- Internet connection
  
To run the tests, execute:

On Linux:
```./mvnw clean test```

On Windows:
```mvnw clean test```

To run a single test:                  

```./mvnw -Dtest=UserListTest#getUserListFromSecondPage test```

## Covered Test Scenarios

- GET pet by ID
- POST create new pet
- PUT update existing pet
- DELETE pet
- HTTP status code validation
- Response body validation
- JSON schema/content validation
- Positive and negative test cases