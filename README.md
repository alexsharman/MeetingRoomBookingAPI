# Meeting Room Booking API

## Example REST API Application using Spring Boot with JSON Web Token

This application has been created for recruitment purposes.

## Main building blocks
 * Spring Boot
 * JSON Web Token 
 * H2 Database Engine 
 * Lombok 
 * Swagger2



## To run the application
The easiest way to build this project is to"

1. Build using maven goal: `mvn clean package` and execute the resulting artifact as follows `java -jar springboot-conference-booking-0.0.1-SNAPSHOT.jar` or
2. On Unix/Linux based systems: run `mvn clean package` then run the resulting jar as any other executable `./springboot-conference-booking-0.0.1-SNAPSHOT.jar`
3. or if you have maven installed use : mvn spring-boot:run


## To test the application

 ### First you will need the following basic pieces of information:

 * client: testjwtclientid
 * secret: XY7kmzoNzl100
 * Non-admin username and password: jsmith and qwerty or jdoe and mySecret
 * Admin user: admin and q1w2e3r4
 * Example of resource accessible to all authenticated users:  http://127.0.0.1:8080/api/v1/rooms/
 * Example of resource accessible to only an admin user:  DEL http://127.0.0.1:8080/api/v1/rooms/Small Room

 1. Generating an access token

   Use the following generic command to generate an access token:
   `$ curl client:secret@localhost:8080/oauth/token -d grant_type=password -d username=user -d password=pwd`

   So for admin it would look like this:
   `curl testjwtclientid:XY7kmzoNzl100@localhost:8080/oauth/token -d grant_type=password -d username=admin -d password=q1w2e3r4`
 
 ## Swagger
 
 You can access swagger here: http://127.0.0.1:8080/swagger-ui.html#/
 

