# Hotel

### Theoretical background (investigate prior to task perform):

1.	HTTP protocol (GET/POST/PUT/DELETE))
2.	Java EE
3.	Spring (especially Spring MVC)
4.	Spring boot
5.	Junit 5
6.	Application servers

### Practice

Create Spring Boot Web application for a hotel with following endpoints:

(* - required fields)
-	booking creation
     
          /booking
          
          PUT:
          -	*Name of Person
          -	*Number of guests (ADULTS and CHILDREN)
          -	*Check-in date
          -	*Check-out date
          -	*Room Type (STANDART, SUITE)
          
          Response:
          -	200 (idBooking)
          -	400
          -	500

-    list of all bookings

          /booking
          
          GET:
          
          Response:
          -	200 (all fields)
          -	500

-    list of defined bookings

          /booking/{idBooking}
          
          GET:
          
          Response:
          -	200 (all fields)
          -	500

-    cancel of booking

          /booking/{idBooking}

          DELETE:

          Response:
          -	200
          -	400
          -	500

-    booking update
       
          /booking/{idBooking}

          UPDATE:
          -	Name of Person
          -	Number of guests (ADULTS and CHILDREN)
          -	Check-in date
          -	Check-out date
          -	Room Type (STANDART, SUITE)
          
          Response:
          -	200
          -	400
          -	500

### Acceptance Criteria:
1.	Project is created
2.	Endpoints are implemented
3.	Request and Response entities are created
4.	Unit tests for endpoints are wrote (coverage 100%)
5.	Swagger is created


#### Note: In this task should be implemented only RestConroller with endpoints which receive request and return some response. No need to create web pages, swagger is enough.

### Technologies and environments to use:
1.	Spring version – latest
2.	Spring boot version – latest
3.	Junit version – 5
4.	Maven should be used as a build system
5.	Application server - tomcat
