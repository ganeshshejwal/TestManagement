# Test Management Project

## Project Description

The Test Management Project is a simple CRUD (Create, Read, Update, Delete) application for managing multiple-choice question tests. <br> It utilizes Spring Boot with Spring Data JPA for database operations and integrates Mockito for unit testing.

### Technologies Used

- Spring Boot 3 (latest version)
- PostgreSQL
- Hibernate (as the JPA implementation)
- JUnit 5
- Gradle (for dependency management)
- Postman (for API testing)
- Logback (logging)

## Project Structure
### Controller Layer

The controller layer handles incoming HTTP requests and routes them to corresponding service methods.

### Service Layer

The service layer contains business logic, exception handling, input data validation.

### Repository Layer

The repository layer interfaces with the database using Spring Data JPA repositories. It implements standard CRUD operations.

### Model Layer

The model layer defines the structure of Model entities using JPA annotations. 

### Database Connectivity

The application utilizes Spring Data JPA and is configured for PostgreSQL database connectivity. Ensure your PostgreSQL database is set up and configured correctly, and update `application.properties` accordingly:

#### Example `application.properties` configuration for PostgreSQL:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/TestManagementDB
spring.datasource.username=postgres
spring.datasource.password=password
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update

logging.file.name=./TestManagementApplication/src/main/java/com/application/testmanagementapplication/logs/Logback.log
```

Replace `jdbc:postgresql://localhost:5432/TestManagementDB` with your actual PostgreSQL database URL, and adjust other properties (`username`, `password`) as needed.

### Testing

Unit tests are implemented using JUnit 5 and Mockito. Tests cover controller and service layer methods.

## Build Steps

Ensure you have ```Java Development Kit (JDK) version 21``` installed on your machine.

1. Clone the repository:
   ```bash
   git clone https://github.com/ganeshshejwal/TestManagement.git
   ```
   
2. Open the project in your preferred Integrated Development Environment (IDE) or navigate to the project directory using a terminal or command prompt.<br>

3. Ensure that you have the ```build.gradle``` file in the project root directory. This file should contain the project configuration and dependencies.<br>

4. Build the project using Gradle by running the following command in the project directory:
   <br>Copy code
   ```bash
   /gradlew build
   ```
This command will compile the source code, run unit tests, and package the application into a JAR file.

## Execution Steps

After successfully building the project, To start the Spring Boot application from the command line navigate to the `build/libs` directory:

```bash
cd build/libs
```
### Running the Application

Run application using following command:

```bash
java -jar TestManagementApplication-0.0.1.jar
```
4. Once the application is running, you can access the API endpoints using a tool like ``` Postman or cURL ```.

#### Ensure your PostgreSQL database (TestManagementDB) is running and correctly configured before starting the application.
